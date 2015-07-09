package com.github.greengerong.tika;

import org.apache.commons.io.FileUtils;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.apache.tika.sax.XHTMLContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import sun.misc.BASE64Encoder;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class TikaDocumentService implements Serializable {

    private static final int MAX_MARK = 100 * 1024 * 1024;
    private static final long serialVersionUID = 5883906936187059495L;
    private static final String OUT_PUT_FILE_TYPE = "html";

    public static void main(String[] args) throws Exception {
        final String file = "/Users/zxgerong/project/opensource/java/normal-spike/src/main/resources/demo.docx";
        final String text = new TikaDocumentService().toHtml(new FileInputStream(file));

        FileUtils.write(new File("test.html"), text);
    }

    public String toHtml(InputStream inputStream) throws Exception {
        makeUnRead(inputStream);

        Parser parser = new AutoDetectParser(TikaConfig.getDefaultConfig());
        ParseContext context = new ParseContext();
        ImageSavingParser imageParser = new ImageSavingParser(parser);
        context.set(Parser.class, imageParser);

        StringWriter htmlBuffer = new StringWriter();
        ContentHandler handler = new BodyContentHandler(getHtmlHandler(htmlBuffer, imageParser));
        parser.parse(inputStream, handler, getDefaultMetadata(), context);

        return replaceImageWithBase64Encode(imageParser, htmlBuffer.toString());
    }

    private String replaceImageWithBase64Encode(ImageSavingParser imageParser, String html) {
        final Map<String, String> imgSrcMap = imageParser.getImgSourceMap();
        for (String uuid : imgSrcMap.keySet()) {
            html = html.replaceFirst(uuid, imgSrcMap.get(uuid));
        }
        return html;
    }

    private Metadata getDefaultMetadata() {
        Metadata md = new Metadata();
        md.set(Metadata.CONTENT_ENCODING, "utf-8");
        return md;
    }

    private void makeUnRead(InputStream inputStream) throws IOException {
        if (inputStream.markSupported()) {
            int mark = -1;
            if (inputStream instanceof TikaInputStream) {
                if (((TikaInputStream) inputStream).hasFile()) {
                    mark = (int) ((TikaInputStream) inputStream).getLength();
                }
            }
            if (mark == -1) {
                mark = MAX_MARK;
            }
            inputStream.mark(mark);
        }
    }

    private ContentHandler getHtmlHandler(Writer writer, ImageSavingParser imageParser)
            throws TransformerConfigurationException {
        SAXTransformerFactory factory = (SAXTransformerFactory)
                SAXTransformerFactory.newInstance();
        TransformerHandler handler = factory.newTransformerHandler();
        handler.getTransformer().setOutputProperty(OutputKeys.METHOD, OUT_PUT_FILE_TYPE);
        handler.setResult(new StreamResult(writer));
        return new ImageContentHandlerDecorator(handler, imageParser);
    }

    private static class ImageSavingParser extends AbstractParser {
        private Map<String, String> wanted = new HashMap<String, String>();
        private Map<String, String> imgSrcSourceMap = new HashMap<String, String>();

        private Parser downstreamParser;

        private ImageSavingParser(Parser downstreamParser) {
            this.downstreamParser = downstreamParser;
        }

        public Map<String, String> getImgSourceMap() {
            return imgSrcSourceMap;
        }

        public void imageMapping(String embeddedName, String uuid) throws IOException {
            wanted.put(embeddedName, uuid);
        }

        public Set<MediaType> getSupportedTypes(ParseContext context) {
            return null;
        }

        public void parse(InputStream stream, ContentHandler handler,
                          Metadata metadata, ParseContext context) throws IOException,
                SAXException, TikaException {
            String name = metadata.get(Metadata.RESOURCE_NAME_KEY);
            if (name != null && wanted.containsKey(name)) {
                final String uuid = wanted.get(name);
                final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                new BASE64Encoder().encodeBuffer(stream, outputStream);
                String image = outputStream.toString();
                imgSrcSourceMap.put(uuid, String.format("data:image/png;base64,%s", image));
            } else {
                if (downstreamParser != null) {
                    downstreamParser.parse(stream, handler, metadata, context);
                }
            }
        }

    }

    private class ImageContentHandlerDecorator extends ContentHandlerDecorator {
        private ImageSavingParser imageParser;

        public ImageContentHandlerDecorator(TransformerHandler handler, ImageSavingParser imageParser) {
            super(handler);
            this.imageParser = imageParser;
        }

        public void startElement(
                String uri, String localName, String name, Attributes atts)
                throws SAXException {
            if (XHTMLContentHandler.XHTML.equals(uri)) {
                uri = null;
            }
            if (!"head".equals(localName)) {
                if ("img".equals(localName)) {
                    AttributesImpl newAttrs;
                    if (atts instanceof AttributesImpl) {
                        newAttrs = (AttributesImpl) atts;
                    } else {
                        newAttrs = new AttributesImpl(atts);
                    }

                    for (int i = 0; i < newAttrs.getLength(); i++) {
                        if ("src".equals(newAttrs.getLocalName(i))) {
                            String src = newAttrs.getValue(i);
                            if (src.startsWith("embedded:")) {
                                String filename = src.substring(src.indexOf(':') + 1);
                                try {
                                    final String uuid = UUID.randomUUID().toString();
                                    imageParser.imageMapping(filename, uuid);
                                    newAttrs.setValue(i, uuid);
                                } catch (Exception e) {
                                    System.err.println("Error creating temp image file " + filename);
                                }
                            }
                        }
                    }
                    super.startElement(uri, localName, name, newAttrs);
                } else {
                    super.startElement(uri, localName, name, atts);
                }
            }
        }
    }
}

package com.github.greengerong;

import java.util.List;
import java.util.stream.Collectors;

import com.beust.jcommander.internal.Lists;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2016                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class NativeScriptFontAwesomeTest {

    @Test
    public void should_generate_icon() throws Exception {

        final List<String> css = IOUtils.readLines(this.getClass().getResourceAsStream("/font-awesome.css"));
        for (int i = 0; i < css.size(); i = i + 4) {
            final String first = css.get(i);
            final String second = css.get(i + 1);
            final String font = second.split(":")[1].trim().replaceAll("\"", "").replace("\\", "");

            final String code = Lists.newArrayList(first.split(","))
                    .stream()
                    .map(t -> {
                        final String[] split = t.split(":");
                        return split[0].trim().substring(1);
                    })
                    .map(t -> String.format("<StackLayout class=\"p-8\">\r\n" +
                            "  <Label class=\"label\" text=\"%s\"></Label>\n" +
                            "  <Label text=\"&#x%s;\" class=\"fa\"></Label>\n" +
                            "</StackLayout>", t, font))
                    .collect(Collectors.joining("\r\n"));

            System.out.println(code);
        }


    }
}

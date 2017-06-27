package com.github.greengerong;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2017                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class ImageIOTest {
    @Test
    public void should_merge_image() throws Exception {

        long start = System.currentTimeMillis();

        final BufferedImage mapImg = ImageIO.read(new File("./src/test/resources/map-example.png"));
        final BufferedImage markImg = ImageIO.read(new File("./src/test/resources/mark.jpeg"));

        BufferedImage combined = new BufferedImage(mapImg.getWidth(), mapImg.getWidth(), BufferedImage.TYPE_INT_ARGB);

        Graphics g = combined.getGraphics();
        g.drawImage(mapImg, 0, 0, null);
        g.setColor(Color.RED);

        ImmutableList
                .of(new Point(100, 50), new Point(100, 200), new Point(310, 140))
                .stream()
                .forEach(point -> {
                    g.drawImage(markImg, point.getX(), point.getY(), 30, 30, null);
                    g.drawString(String.format("%d,%d", point.getX(), point.getY()), point.getX(), point.getY() - 10);
                });


        ImageIO.write(combined, "PNG", new File("./target", "map-combined.png"));

        final long end = System.currentTimeMillis();
        System.out.println("performance: " + (end - start));
    }
}


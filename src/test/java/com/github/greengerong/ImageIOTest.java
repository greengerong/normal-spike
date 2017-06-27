package com.github.greengerong;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

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

        final String mapUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/79/Topographic_map_example.png/440px-Topographic_map_example.png";
        final BufferedImage mapImg = ImageIO.read(new URL(mapUri));
        System.out.println(this.getClass().getResource("fav_checked.png") + "=======");
        final BufferedImage markImg = ImageIO.read(this.getClass().getResource("fav_checked.png"));


        BufferedImage combined = new BufferedImage(mapImg.getWidth(), mapImg.getWidth(), BufferedImage.TYPE_INT_ARGB);

        Graphics g = combined.getGraphics();
        g.drawImage(mapImg, 0, 0, null);
        g.drawImage(markImg, 0, 0, null);

        ImageIO.write(mapImg, "PNG", new File("./target", "map-combined.png"));
    }
}

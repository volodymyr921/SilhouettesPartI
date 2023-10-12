package com.shpp.p2p.cs.vomelianchuk.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assignment12Part1 {
    public static void main(String[] args) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(args[0]));
        } catch (IOException e) {
            try {
                image = ImageIO.read(new File("test.jpg"));
            } catch (IOException ex) {
                System.err.println("The file \"test.jpg\" does not exist in the project");
            }
        }

        if (image != null) {
            System.out.println(image.getWidth());
            int[][] arrayPixels = new int[image.getWidth()][image.getHeight()];
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    System.out.print(image.getRGB(i, j)+  ",");
                }
                System.out.println();
            }
            Color pixelColor = new Color(image.getRGB(0, 0), true);
            System.out.println(image.getRGB(0,0));
            System.out.println(pixelColor);
            System.out.println(pixelColor.getAlpha());
            
            long rgb = image.getRGB(0, 0);
            long red = (rgb >> 16) & 0xFF;
            long green = (rgb >> 8) & 0xFF;
            long blue = rgb & 0xFF;
            System.out.println(red);
            System.out.println(green);
            System.out.println(blue);

            Color pixelColor1 = new Color(image.getRGB(9, 9), true);
            System.out.println(image.getRGB(9, 9));
            System.out.println(pixelColor1);
            System.out.println(pixelColor1.getAlpha());

            Color pixelColor2 = new Color(image.getRGB(9, 7), true);
            System.out.println(image.getRGB(9, 7));
            System.out.println(pixelColor2);
            System.out.println(pixelColor2.getAlpha());


        }

    }
}

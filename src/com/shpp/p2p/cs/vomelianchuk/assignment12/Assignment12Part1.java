package com.shpp.p2p.cs.vomelianchuk.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assignment12Part1 {

    public static final int COLOR_CHANNEL_DEVIATION = 100;

    public static void main(String[] args) {

        BufferedImage image = getImage(args[0]);

        Pixel[][] pixels = getAllPixels(image);
        Color firstColor = getFirstColor(pixels);
        Color secondColor = getSecondColor(pixels, firstColor);
//        System.out.println(firstColor);
//        System.out.println(secondColor);

        Color background = defineTheBackground(pixels, firstColor, secondColor);
        Color colorSilhouettes = (firstColor.equals(background)) ? secondColor : firstColor;

        Integer[][] imageMatrix = createImageMatrix(pixels, colorSilhouettes);
        DepthFirstSearch search = new DepthFirstSearch(imageMatrix);
        System.out.println(args[0] + " has " + search.calculateNumberOfSilhouettes() + " silhouettes!");
    }

    private static BufferedImage getImage(String file) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            try {
                image = ImageIO.read(new File("test.jpg"));
            } catch (IOException ex) {
                System.err.println("The file \"test.jpg\" does not exist in the project");
            }
        }

        return image;
    }

    private static Pixel[][] getAllPixels(BufferedImage image) {
        Pixel[][] pixels = new Pixel[image.getHeight()][image.getWidth()];

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixels[j][i] = getPixel(image, i, j);
            }
        }

        return pixels;
    }

    private static Pixel getPixel(BufferedImage image, int row, int column) {
        long rgb = image.getRGB(row, column);
        int red = (int)(rgb >> 16) & 0xFF;
        int green = (int)(rgb >> 8) & 0xFF;
        int blue = (int)rgb & 0xFF;

        return new Pixel(red, green, blue);
    }
    private static Color getFirstColor(Pixel[][] pixels) {
        int[] rgb = getRgb(pixels[0][0]);

        return new Color(rgb[0], rgb[1], rgb[2]);
    }

    private static int[] getRgb(Pixel pixel) {
        int[] rgb = new int[3];

        rgb[0]  = pixel.getRed();
        rgb[1] = pixel.getGreen();
        rgb[2] = pixel.getBlue();

        return rgb;
    }

    private static Color getSecondColor(Pixel[][] pixels, Color firstColor) {
        Color secondColor = null;

        for (Pixel[] pixelsRow : pixels) {
            for (Pixel pixel : pixelsRow) {
                int[] rgb = getRgb(pixel);
                secondColor = new Color(rgb[0], rgb[1], rgb[2]);
                if (!areTheSameColors(firstColor, secondColor)) {
                    return secondColor;
                }
            }
        }

        return secondColor;
    }

    private static boolean areTheSameColors(Color firstColor, Color secondColor) {

//        if(Math.abs(firstColor.getRed() - secondColor.getRed()) <= COLOR_CHANNEL_DEVIATION) {
//            return true;
//        } else if (Math.abs(firstColor.getGreen() - secondColor.getGreen()) <= COLOR_CHANNEL_DEVIATION) {
//            return true;
//        } else return Math.abs(firstColor.getBlue() - secondColor.getBlue()) <= COLOR_CHANNEL_DEVIATION;
        return Math.abs(firstColor.getRed() - secondColor.getRed()) <= COLOR_CHANNEL_DEVIATION &&
                Math.abs(firstColor.getGreen() - secondColor.getGreen()) <= COLOR_CHANNEL_DEVIATION &&
                Math.abs(firstColor.getBlue() - secondColor.getBlue()) <= COLOR_CHANNEL_DEVIATION;
    }

    private static Color defineTheBackground(Pixel[][] pixels, Color firstColor, Color secondColor) {
        Color color = null;
        int countPixelsFirstColor = 0;
        int countPixelsSecondColor = 0;

        for (Pixel[] pixelsRow : pixels) {
            for (Pixel pixel : pixelsRow) {
                int[] rgb = getRgb(pixel);
                color = new Color(rgb[0], rgb[1], rgb[2]);
                if(areTheSameColors(color, firstColor)) countPixelsFirstColor++;
                if(areTheSameColors(color, secondColor)) countPixelsSecondColor++;
            }
        }

        if(countPixelsFirstColor >= countPixelsSecondColor) return firstColor;
        return secondColor;
    }

    private static Integer[][] createImageMatrix(Pixel[][] pixels, Color colorSilhouettes) {
        Integer[][] matrix = new Integer[pixels.length][pixels[0].length];
        Color color = null;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int[] rgb = getRgb(pixels[i][j]);
                color = new Color(rgb[0], rgb[1], rgb[2]);
                matrix[i][j] = areTheSameColors(color, colorSilhouettes) ? 1 : 0;
            }
        }

        return matrix;
    }

}

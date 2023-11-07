package com.shpp.p2p.cs.vomelianchuk.assignment12;

import java.awt.*;

/**
 * ColorProcessing.java
 * --------------------
 * Work with a pixel matrix, processes colors,
 * find RGB values and compares its
 */
public class ColorProcessing {
    // color deviation is allowed
    public static final int COLOR_CHANNEL_DEVIATION = 50;

    private final Pixel[][] pixels;

    /**
     * Creates a pixel matrix object
     *
     * @param pixels All the pixels of the image
     */
    public ColorProcessing(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    /**
     * Gets the first color from the first pixel
     *
     * @return The first color
     */
    public Color getFirstColor() {
        int[] rgb = getRgb(pixels[0][0]);

        return new Color(rgb[0], rgb[1], rgb[2]);
    }

    /**
     * Receives three color values from a pixel (red, green, blue)
     * and writes them to the array in order
     *
     * @param pixel The pixel that needs to be colored
     * @return An array of three colors: red, green, blue
     */
    private static int[] getRgb(Pixel pixel) {
        int[] rgb = new int[3];

        rgb[0]  = pixel.getRed();
        rgb[1] = pixel.getGreen();
        rgb[2] = pixel.getBlue();

        return rgb;
    }

    /**
     * Analyzes the entire image and finds the most distant color
     * from the first one found earlier
     *
     * @param firstColor The first color that has already been received
     * @return The second color
     */
    public Color getSecondColor(Color firstColor) {
        // keep the maximum deviation of each color from the first color
        int maxRedDiff = 0;
        int maxGreenDiff = 0;
        int maxBlueDiff = 0;

        // store the color values that will be used to create a new color
        int red = 0;
        int green = 0;
        int blue = 0;

        for (Pixel[] pixelsRow : pixels) {
            for (Pixel pixel : pixelsRow) {
                int[] rgb = getRgb(pixel);

                // deviation of the current pixel
                int redDiff = Math.abs(rgb[0] - firstColor.getRed());
                int greenDiff = Math.abs(rgb[1] - firstColor.getGreen());
                int blueDiff = Math.abs(rgb[2] - firstColor.getBlue());

                // comparing the current deviation with the maximum and updating the color values
                if (redDiff > maxRedDiff) {
                    maxRedDiff = redDiff;
                    red = rgb[0];
                }
                if (greenDiff > maxGreenDiff) {
                    maxGreenDiff = greenDiff;
                    green = rgb[1];
                }
                if (blueDiff > maxBlueDiff) {
                    maxBlueDiff = blueDiff;
                    blue = rgb[2];
                }
            }
        }

        return new Color(red, green, blue);
    }

    /**
     * Compares two colors and determines
     * whether they are similar to each other
     *
     * @param firstColor The first color
     * @param secondColor The second color
     * @return Whether these colors are similar
     */
    private boolean areTheSameColors(Color firstColor, Color secondColor) {
        return Math.abs(firstColor.getRed() - secondColor.getRed()) <= COLOR_CHANNEL_DEVIATION &&
                Math.abs(firstColor.getGreen() - secondColor.getGreen()) <= COLOR_CHANNEL_DEVIATION &&
                Math.abs(firstColor.getBlue() - secondColor.getBlue()) <= COLOR_CHANNEL_DEVIATION;
    }

    /**
     * Determines which of two colors is the background color
     *
     * @param firstColor The first color
     * @param secondColor The second color
     * @return The color that is the background for the image
     */
    public Color defineTheBackground(Color firstColor, Color secondColor) {
        int countPixelsFirstColor = 0;
        int countPixelsSecondColor = 0;

        // finds a way where more pixels of the same color means it's the background
        for (Pixel[] pixelsRow : pixels) {
            for (Pixel pixel : pixelsRow) {
                int[] rgb = getRgb(pixel);
                Color color = new Color(rgb[0], rgb[1], rgb[2]);
                if(areTheSameColors(color, firstColor)) countPixelsFirstColor++;
                if(areTheSameColors(color, secondColor)) countPixelsSecondColor++;
            }
        }

        if(countPixelsFirstColor >= countPixelsSecondColor) return firstColor;
        return secondColor;
    }

    /**
     * Compares the color of each pixel of the image
     * with the background and converts it into a numeric matrix,
     * where 1 is the silhouette, 0 is the background
     *
     * @param background The background color
     * @return Numerical matrix of 0 and 1
     */
   public Integer[][] createImageMatrix(Color background) {
        Integer[][] matrix = new Integer[pixels.length][pixels[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int[] rgb = getRgb(pixels[i][j]);
                Color color = new Color(rgb[0], rgb[1], rgb[2]);
                matrix[i][j] = areTheSameColors(color, background) ? 0 : 1;
            }
        }

        return matrix;
    }
}

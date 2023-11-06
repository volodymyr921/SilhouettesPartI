package com.shpp.p2p.cs.vomelianchuk.assignment12;

import java.awt.*;

public class ColorProcessing {
    public static final int COLOR_CHANNEL_DEVIATION = 50;

    private final Pixel[][] pixels;

    public ColorProcessing(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    public Color getFirstColor() {
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

    public Color getSecondColor(Color firstColor) {
        int maxRedDiff = 0;
        int maxGreenDiff = 0;
        int maxBlueDiff = 0;

        int red = 0;
        int green = 0;
        int blue = 0;

        for (Pixel[] pixelsRow : pixels) {
            for (Pixel pixel : pixelsRow) {
                int[] rgb = getRgb(pixel);

                int redDiff = Math.abs(rgb[0] - firstColor.getRed());
                int greenDiff = Math.abs(rgb[1] - firstColor.getGreen());
                int blueDiff = Math.abs(rgb[2] - firstColor.getBlue());

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

    private boolean areTheSameColors(Color firstColor, Color secondColor) {
        return Math.abs(firstColor.getRed() - secondColor.getRed()) <= COLOR_CHANNEL_DEVIATION &&
                Math.abs(firstColor.getGreen() - secondColor.getGreen()) <= COLOR_CHANNEL_DEVIATION &&
                Math.abs(firstColor.getBlue() - secondColor.getBlue()) <= COLOR_CHANNEL_DEVIATION;
    }

    public Color defineTheBackground(Color firstColor, Color secondColor) {
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

   public Integer[][] createImageMatrix(Color background) {
        Integer[][] matrix = new Integer[pixels.length][pixels[0].length];
        Color color = null;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int[] rgb = getRgb(pixels[i][j]);
                color = new Color(rgb[0], rgb[1], rgb[2]);
                matrix[i][j] = areTheSameColors(color, background) ? 0 : 1;
            }
        }

        return matrix;
    }
}

package com.shpp.p2p.cs.vomelianchuk.assignment12;

import java.awt.image.BufferedImage;

/**
 * PixelsProcessing.java
 * ---------------------
 * Processes all pixels from the image
 * and receives a matrix of pixels based on this
 */
public class PixelsProcessing {
    private final BufferedImage image;

    /**
     * Creates an object with an image
     *
     * @param image The image to be processed
     */
    public PixelsProcessing(BufferedImage image) {
        this.image = image;
    }

    /**
     * Converts an image into a matrix of pixels
     *
     * @return The matrix of pixels
     */
   public Pixel[][] getAllPixels() {
        Pixel[][] pixels = new Pixel[image.getHeight()][image.getWidth()];
        // go through all the pixels of the image and write them into the matrix
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixels[j][i] = getPixel(i, j);
            }
        }

        return pixels;
    }

    /**
     * Get pixel based colors from RGB format
     *
     * @param indexRow Current row index
     * @param indexColumn Current column index
     * @return The pixel with processed colors
     */
    private Pixel getPixel(int indexRow, int indexColumn) {
        long rgb = image.getRGB(indexRow, indexColumn);

        int red = (int)(rgb >> 16) & 0xFF;
        int green = (int)(rgb >> 8) & 0xFF;
        int blue = (int)rgb & 0xFF;

        return new Pixel(red, green, blue);
    }
}

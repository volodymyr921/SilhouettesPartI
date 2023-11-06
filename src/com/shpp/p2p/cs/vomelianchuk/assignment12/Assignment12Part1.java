package com.shpp.p2p.cs.vomelianchuk.assignment12;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Assignment12Part1.java
 * ----------------------
 * The main class of the program receives the name of the picture
 * and calculates the number of depicted silhouettes
 */
public class Assignment12Part1 {

    public static void main(String[] args) {
        // get the image from the arguments
        ImageProcessing image = new ImageProcessing(args);
        BufferedImage processedImage = image.getProcessedImage();

        // get the pixel matrix from the given image
        PixelsProcessing  imagePixels = new PixelsProcessing(processedImage);
        Pixel[][] pixels = imagePixels.getAllPixels();

        // get two different colors and define the background of the image
        ColorProcessing colorProcessing = new ColorProcessing(pixels);
        Color firstColor = colorProcessing.getFirstColor();
        Color secondColor = colorProcessing.getSecondColor(firstColor);
        Color background = colorProcessing.defineTheBackground(firstColor, secondColor);

        // convert pixel matrix to numeric matrix
        Integer[][] imageMatrix = colorProcessing.createImageMatrix(background);

        // calculate the number of silhouettes by recursive depth-first search
        DepthFirstSearch search = new DepthFirstSearch(imageMatrix);
        int numberOfSilhouettes = search.calculateNumberOfSilhouettes();

        displayTheResult(image, numberOfSilhouettes);
    }

    /**
     * Outputs the result to the console
     *
     * @param image Current image
     * @param numberOfSilhouettes number of silhouettes
     */
    private static void displayTheResult(ImageProcessing image, int numberOfSilhouettes) {
        System.out.println(image.getNameFile() + " has " + numberOfSilhouettes + " silhouettes!");
    }
}

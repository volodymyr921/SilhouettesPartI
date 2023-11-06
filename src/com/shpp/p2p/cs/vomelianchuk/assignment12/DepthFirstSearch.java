package com.shpp.p2p.cs.vomelianchuk.assignment12;

import java.util.ArrayList;
import java.util.List;

/**
 * DepthFirstSearch.java
 * ---------------------
 * The class that is responsible for counting silhouettes
 * using a recursive depth-first search to "walk" the silhouette,
 * as well as discarding small garbage for correct counting
 */
public class DepthFirstSearch {
    // Percentage threshold from the largest silhouette, anything less is small garbage
    private static final double GARBAGE_SIZE = 0.05;

    // Image in the form of a numerical matrix, where silhouette pixels = 1, and background pixels = 0
    private final Integer[][] matrixImage;

    // List to which the total number of pixels of each silhouette will be written
    List<Integer> pixelsSilhouettes;

    public DepthFirstSearch(Integer[][] matrixImage) {
        this.matrixImage = matrixImage;
        pixelsSilhouettes = new ArrayList<>();
    }

    /**
     * The method that, on finding a 1 in the array,
     * runs a depth-first recursive search to find
     * the total number of silhouette pixels,
     * and calls a method that discards the small garbage
     * and counts the total number of silhouettes
     *
     * @return Number of silhouettes
     */
    public int calculateNumberOfSilhouettes() {
        for (int i = 0; i < matrixImage.length; i++) {
            for (int j = 0; j < matrixImage[0].length; j++) {
                if (matrixImage[i][j] == 1) {
                    pixelsSilhouettes.add(searchDepthFirst(i, j));
                }
            }
        }

        return garbageDisposalAndCountSilhouettes();
    }

    /**
     * Throws away fine garbage from silhouette counting.
     * If the number of pixels of the silhouette is less than 5% of the number
     * of pixels of the largest silhouette, then we consider it garbage.
     * Then we count the total number of silhouettes
     *
     * @return Number of silhouettes
     */
    private int garbageDisposalAndCountSilhouettes() {
        int amountSilhouettes = 0;
        int maxPixels = 0;

        // look for the silhouette with the highest number of pixels
        for (int pixels : pixelsSilhouettes) {
            maxPixels = Math.max(maxPixels, pixels);
        }
        // count the number of silhouettes, without small garbage
        for (int pixels : pixelsSilhouettes) {
            if ((double) pixels / maxPixels > GARBAGE_SIZE) {
                amountSilhouettes++;
            }
        }

        return amountSilhouettes;
    }

    /**
     * The method of recursive search in depth for "bypassing" the silhouette.
     * Calculate the number of pixels in the silhouette
     *
     * @param indexRow The index of the current row
     * @param indexCol The index of the current column
     * @return  The number of pixels in the silhouette
     */
    private int searchDepthFirst(int indexRow, int indexCol) {
        // check whether it does not go beyond the matrix
        if(indexRow < 0 || indexRow >= matrixImage.length || indexCol < 0 || indexCol >= matrixImage[0].length) return 0;

        // check if this pixel belongs to the background
        if(matrixImage[indexRow][indexCol] != 1) return 0;

        matrixImage[indexRow][indexCol] = 0;
        int pixels = 1;

        // call the recursive method on all pixels adjacent to this one
        pixels += searchDepthFirst(indexRow , indexCol + 1);
        pixels += searchDepthFirst(indexRow + 1, indexCol);
        pixels += searchDepthFirst(indexRow, indexCol - 1);
        pixels += searchDepthFirst(indexRow - 1, indexCol);

        return pixels;
    }
}

package com.shpp.p2p.cs.vomelianchuk.assignment12;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {
    private static final double GARBAGE_SIZE = 0.05;

    private final Integer[][] matrixImage;
    int[][] directions;
    List<Integer> pixelsSilhouettes;

    public DepthFirstSearch(Integer[][] matrixImage) {
        this.matrixImage = matrixImage;
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        pixelsSilhouettes = new ArrayList<>();
    }

    public int calculateNumberOfSilhouettes() {

        for (int i = 0; i < matrixImage.length; i++) {
            for (int j = 0; j < matrixImage[0].length; j++) {
                if (matrixImage[i][j] == 1) {
                    pixelsSilhouettes.add(searchDepthFirst(i, j));
                }
            }
        }
        return garbageDisposal();
    }

    private int garbageDisposal() {
        int amount = 0;
        int maxPixels = 0;
        for (int pixels : pixelsSilhouettes) {
            maxPixels = Math.max(maxPixels, pixels);
            //System.out.println("PIXELS: " + pixels);
        }
        for (int pixels : pixelsSilhouettes) {
            if ((double) pixels / maxPixels > GARBAGE_SIZE) {
                amount++;
            }
        }
        return amount;
    }

    private int searchDepthFirst(int row, int col) {

        if(row < 0 || row >= matrixImage.length || col < 0 || col >= matrixImage[0].length) return 0;
        if(matrixImage[row][col] != 1) return 0;
        matrixImage[row][col] = 0;

        int pixels = 1;
        for (int[] direction : directions) {
            int di = direction[0];
            int dj = direction[1];

            pixels += searchDepthFirst(row + di, col + dj);
        }

        return pixels;
    }
}

package com.shpp.p2p.cs.vomelianchuk.assignment12;

/**
 * Pixel.java
 * ----------
 * The class that describes a pixel and its three characteristic colors
 */
public class Pixel {

    private final int red;
    private final int green;
    private final int blue;

    /**
     * The constructor creates an object with three colors in RGB format
     *
     * @param red Red color
     * @param green Green color
     * @param blue Blue color
     */
    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Returns the value of red color in RGB format
     *
     * @return Red color in RGB representation
     */
    public int getRed() {
        return red;
    }

    /**
     * Returns the value of green color in RGB format
     *
     * @return Green color in RGB representation
     */
    public int getGreen() {
        return green;
    }

    /**
     * Returns the value of blue color in RGB format
     *
     * @return Blue color in RGB representation
     */
    public int getBlue() {
        return blue;
    }
}

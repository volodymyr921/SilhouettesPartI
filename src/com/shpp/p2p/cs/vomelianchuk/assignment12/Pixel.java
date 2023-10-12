package com.shpp.p2p.cs.vomelianchuk.assignment12;

public class Pixel {
    private final long red;
    private final long green;
    private final long blue;

    public Pixel(long red, long green, long blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public long getRed() {
        return red;
    }

    public long getGreen() {
        return green;
    }

    public long getBlue() {
        return blue;
    }
}

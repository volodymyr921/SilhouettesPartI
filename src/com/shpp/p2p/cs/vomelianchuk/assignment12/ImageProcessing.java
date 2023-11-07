package com.shpp.p2p.cs.vomelianchuk.assignment12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ImageProcessing.java
 * --------------------
 * The class receives the file, processes it and returns the image
 */
public class ImageProcessing {
    private String file;
    private BufferedImage image;

    /**
     * Create an object: the name or path of the picture,
     * if it is not present, it creates the picture by default
     *
     * @param pathName The path to the image name
     */
    public ImageProcessing(String[] pathName) {
        try {
            file = pathName[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            file = "test.jpg";
        }

    }

    /**
     * Read an image from filename and return it
     *
     * @return The image object
     */
    public BufferedImage getProcessedImage() {
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

    /**
     * Return the name of the file
     *
     * @return The name of the file
     */
    public String getNameFile() {
        return file;
    }
}

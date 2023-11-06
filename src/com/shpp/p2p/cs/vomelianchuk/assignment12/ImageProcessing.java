package com.shpp.p2p.cs.vomelianchuk.assignment12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {
    private String file;
    private BufferedImage image;

    public ImageProcessing(String[] pathName) {
        try {
            file = pathName[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            file = "test.jpg";
        }

    }
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

    public String getNameFile() {
        return file;
    }
}

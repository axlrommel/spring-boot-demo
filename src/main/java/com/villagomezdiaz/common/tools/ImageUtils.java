package com.villagomezdiaz.common.tools;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class ImageUtils {
    /**
     * Clones a BufferedImage.
     * 
     * @param image the image to clone
     * @return the cloned image
     */
    public static BufferedImage cloneImage(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        g.drawRenderedImage(image, null);
        g.dispose();
        return newImage;
    }

    /**
     * A convenience method for getting ARGB pixels from an image. This tries to
     * avoid the performance
     * penalty of BufferedImage.getRGB unmanaging the image.
     * 
     * @param image  a BufferedImage object
     * @param x      the left edge of the pixel block
     * @param y      the right edge of the pixel block
     * @param width  the width of the pixel arry
     * @param height the height of the pixel arry
     * @param pixels the array to hold the returned pixels. May be null.
     * @return the pixels
     * @see #setRGB
     */
    public static int[] getRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels) {
        int type = image.getType();
        if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB)
            return (int[]) image.getRaster().getDataElements(x, y, width, height, pixels);
        return image.getRGB(x, y, width, height, pixels, 0, width);
    }

    /**
     * A convenience method for setting ARGB pixels in an image. This tries to avoid
     * the performance
     * penalty of BufferedImage.setRGB unmanaging the image.
     * 
     * @param image  a BufferedImage object
     * @param x      the left edge of the pixel block
     * @param y      the right edge of the pixel block
     * @param width  the width of the pixel arry
     * @param height the height of the pixel arry
     * @param pixels the array of pixels to set
     * @see #getRGB
     */
    public static void setRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels) {
        int type = image.getType();
        if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB)
            image.getRaster().setDataElements(x, y, width, height, pixels);
        else
            image.setRGB(x, y, width, height, pixels, 0, width);
    }

    /**
     * Returns a *copy* of a subimage of image. This avoids the performance problems
     * associated with BufferedImage.getSubimage.
     * 
     * @param image the image
     * @param x     the x position
     * @param y     the y position
     * @param w     the width
     * @param h     the height
     * @return the subimage
     */
    public static BufferedImage getSubimage(BufferedImage image, int x, int y, int w, int h) {
        BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        g.drawRenderedImage(image, AffineTransform.getTranslateInstance(-x, -y));
        g.dispose();
        return newImage;
    }
}

package com.villagomezdiaz.common.utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.jhlabs.image.EdgeFilter;
import com.villagomezdiaz.common.Constants;

public class BackgroundBlacker {
    public static BufferedImage convertFromAllSides(BufferedImage input, int threshold) {
        EdgeFilter filter = new EdgeFilter();
        BufferedImage imageOut = ImageUtils.cloneImage(input);
        BufferedImage tmpImage = null;
        tmpImage = filter.filter(input, tmpImage);

        for (int i = 0; i < tmpImage.getHeight(); i++) {
            for (int j = 0; j < tmpImage.getWidth(); j++) {
                Color c = new Color(tmpImage.getRGB(j, i));
                if (c.getRed() < threshold && c.getGreen() < threshold && c.getBlue() < threshold) {
                    ImageUtils.setRGB(imageOut, j, i, 1, 1, Constants.COLOR_BLACK);
                } else {
                    break;
                }
            }
        }

        for (int i = 0; i < tmpImage.getHeight(); i++) {
            for (int j = tmpImage.getWidth() - 1; j >= 0; j--) {
                Color c = new Color(tmpImage.getRGB(j, i));
                if (c.getRed() < threshold && c.getGreen() < threshold && c.getBlue() < threshold) {
                    ImageUtils.setRGB(imageOut, j, i, 1, 1, Constants.COLOR_BLACK);
                } else {
                    break;
                }
            }
        }

        for (int j = 0; j < tmpImage.getWidth(); j++) {
            for (int i = 0; i < tmpImage.getHeight(); i++) {
                Color c = new Color(tmpImage.getRGB(j, i));
                if (c.getRed() < threshold && c.getGreen() < threshold && c.getBlue() < threshold) {
                    ImageUtils.setRGB(imageOut, j, i, 1, 1, Constants.COLOR_BLACK);
                } else {
                    break;
                }
            }
        }

        for (int j = 0; j < tmpImage.getWidth(); j++) {
            for (int i = tmpImage.getHeight() - 1; i >= 0; i--) {
                Color c = new Color(tmpImage.getRGB(j, i));
                if (c.getRed() < threshold && c.getGreen() < threshold && c.getBlue() < threshold) {
                    ImageUtils.setRGB(imageOut, j, i, 1, 1, Constants.COLOR_BLACK);
                } else {
                    break;
                }
            }
        }

        return imageOut;
    }
}

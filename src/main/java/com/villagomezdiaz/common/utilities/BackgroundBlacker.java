package com.villagomezdiaz.common.utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.jhlabs.image.EdgeFilter;
import com.villagomezdiaz.common.Constants;
import com.villagomezdiaz.jhlabs.ImageUtils;

public class BackgroundBlacker {

    public static BufferedImage convertFromCenter(BufferedImage input) {
        return convertFromCenter(input, 0);
    }

    public static BufferedImage convertFromCenter(BufferedImage input, int threshold) {
        EdgeFilter laplace = new EdgeFilter();
        BufferedImage imageOut = ImageUtils.cloneImage(input);
        BufferedImage tmpImage = null;
        tmpImage = laplace.filter(input, tmpImage);

        for(int i = 0; i < tmpImage.getHeight(); i++) {
            for(int j = 0; j < tmpImage.getWidth(); j ++) {
                Color c = new Color(tmpImage.getRGB(j, i));
                if(c.getRed() < threshold && c.getGreen() < threshold && c.getBlue() < threshold) {
                    ImageUtils.setRGB(imageOut, j, i, 1, 1, Constants.COLOR_BLACK);
                }
                else {
                    break;
                }
            }
        }

        //now let's do it from the right
	for(int i = 0; i < tmpImage.getHeight(); i++) {
		for(int j = tmpImage.getWidth() -1 ; j >= 0; j --) {
			Color c = new Color(tmpImage.getRGB(j, i));
			if(c.getRed() < threshold && c.getGreen() < threshold && c.getBlue() < threshold) {
				ImageUtils.setRGB(imageOut, j, i, 1, 1, Constants.COLOR_BLACK);
			}
			else {
				break;
			}
		}
	}

    for(int j = 0; j < tmpImage.getWidth(); j++) {
		for(int i = 0; i < tmpImage.getHeight(); i ++) {
			Color c = new Color(tmpImage.getRGB(j, i));
			if(c.getRed() < threshold && c.getGreen() < threshold && c.getBlue() < threshold) {
				ImageUtils.setRGB(imageOut, j, i, 1, 1, Constants.COLOR_BLACK);
			}
			else {
				break;
			}
		}
	}

    //now let's do it from the right
	for(int j = 0; j < tmpImage.getWidth(); j++) {
		for(int i = tmpImage.getHeight() -1 ; i >= 0; i --) {
			Color c = new Color(tmpImage.getRGB(j, i));
			if(c.getRed() < threshold && c.getGreen() < threshold && c.getBlue() < threshold) {
				ImageUtils.setRGB(imageOut, j, i, 1, 1, Constants.COLOR_BLACK);
			}
			else {
				break;
			}
		}
	}


        
        return imageOut;
    }
}

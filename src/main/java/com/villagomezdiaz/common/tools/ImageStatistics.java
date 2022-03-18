package com.villagomezdiaz.common.tools;

import java.awt.image.BufferedImage;

public class ImageStatistics {

    private double[] reds = new double[255];
    private double[] greens = new double[255];
    private double[] blues = new double[255];

    public ImageStatistics(BufferedImage img) {

        super();
        int imageHeight = img.getHeight();
        int imageWidth = img.getWidth();

        int[] outPixels = ImageUtils.getRGB(img, 0, 0, imageWidth, imageHeight, null);
        Histogram hist = new Histogram(outPixels, imageWidth, imageHeight, 0, imageWidth);

        // ignore i = 0;
        for (int i = 0; i < 255; i++) {
            reds[i] = (double) hist.getFrequency(Histogram.RED, i + 1);
            greens[i] = (double) hist.getFrequency(Histogram.GREEN, i + 1);
            blues[i] = (double) hist.getFrequency(Histogram.BLUE, i + 1);
        }

    }

    public ImageStatistics(double[][] colors) {
        reds = colors[0];
        greens = colors[1];
        blues = colors[2];
    }

    public double[][] getAllColors() {
        return new double[][] { reds, greens, blues };
    }

    public double[] getReds() {
        return reds;
    }

    public double[] getGreens() {
        return greens;
    }

    public double[] getBlues() {
        return blues;
    }

}

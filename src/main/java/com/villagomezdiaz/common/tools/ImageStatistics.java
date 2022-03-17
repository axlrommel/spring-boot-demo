package com.villagomezdiaz.common.tools;

import java.awt.image.BufferedImage;

import com.jhlabs.image.Histogram;
import com.villagomezdiaz.common.utilities.ImageUtils;

public class ImageStatistics {
    private static int COLOR_SHIFT_LIGHT_DARK = 200;

    // 0 - 255
    private double[] reds = new double[255];
    private double[] greens = new double[255];
    private double[] blues = new double[255];

    // 0 - 200 approx
    private double[] lowReds = new double[COLOR_SHIFT_LIGHT_DARK];
    private double[] lowGreens = new double[COLOR_SHIFT_LIGHT_DARK];
    private double[] lowBlues = new double[COLOR_SHIFT_LIGHT_DARK];

    // 55 - 255 approx
    private double[] highReds = new double[COLOR_SHIFT_LIGHT_DARK];
    private double[] highGreens = new double[COLOR_SHIFT_LIGHT_DARK];
    private double[] highBlues = new double[COLOR_SHIFT_LIGHT_DARK];

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

        initRestOfValues();

    }

    public ImageStatistics(String[] r, String[] g, String[] b) {

        for (int i = 0; i < 255; i++) {
            reds[i] = Double.valueOf(r[i]).doubleValue();
            greens[i] = Double.valueOf(g[i]).doubleValue();
            blues[i] = Double.valueOf(b[i]).doubleValue();
        }

        initRestOfValues();
    }

    public ImageStatistics(double[] r, double[] g, double[] b) {

        for (int i = 0; i < 255; i++) {
            reds[i] = r[i];
            greens[i] = g[i];
            blues[i] = b[i];
        }

        initRestOfValues();
    }

    private void initRestOfValues() {

        for (int i = 0; i < COLOR_SHIFT_LIGHT_DARK; i++) {
            lowReds[i] = reds[i];
            lowGreens[i] = greens[i];
            lowBlues[i] = blues[i];
            highReds[i] = reds[i + 255 - COLOR_SHIFT_LIGHT_DARK];
            highGreens[i] = greens[i + 255 - COLOR_SHIFT_LIGHT_DARK];
            highBlues[i] = blues[i + 255 - COLOR_SHIFT_LIGHT_DARK];
        }
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

    public double[] getLowReds() {
        return lowReds;
    }

    public double[] getLowGreens() {
        return lowGreens;
    }

    public double[] getLowBlues() {
        return lowBlues;
    }

    public double[] getHighReds() {
        return highReds;
    }

    public double[] getHighGreens() {
        return highGreens;
    }

    public double[] getHighBlues() {
        return highBlues;
    }
}

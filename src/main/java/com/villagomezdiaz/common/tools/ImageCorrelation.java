package com.villagomezdiaz.common.tools;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class ImageCorrelation {

    double redCorr = 0;
    double greenCorr = 0;
    double blueCorr = 0;
    double overallCorr = 0;

    public ImageCorrelation(ImageStatistics statImg1, ImageStatistics statImg2) {
        PearsonsCorrelation corr = new PearsonsCorrelation();

        this.redCorr = corr.correlation(statImg1.getReds(), statImg2.getReds());
        this.greenCorr = corr.correlation(statImg1.getGreens(), statImg2.getGreens());
        this.blueCorr = corr.correlation(statImg1.getBlues(), statImg2.getBlues());

        this.overallCorr = redCorr + greenCorr + blueCorr;
    }

    public double getRedCorr() {
        return redCorr;
    }

    public double getGreenCorr() {
        return greenCorr;
    }

    public double getBlueCorr() {
        return blueCorr;
    }

    public double getOverallCorr() {
        return overallCorr;
    }

    @Override
    public String toString() {
        return "ImageCorrelation [redCorr=" + redCorr + ", greenCorr=" + greenCorr + ", blueCorr=" + blueCorr
                + ", overallCorr=" + overallCorr + "]";
    }
}

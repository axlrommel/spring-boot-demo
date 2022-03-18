package com.villagomezdiaz.common.utilities;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.villagomezdiaz.common.tools.ImageCorrelation;
import com.villagomezdiaz.common.tools.ImageStatistics;

public class FindImageMatches {
    public static void process(BufferedImage image, HashMap<String, double[][]> store) {
        ImageStatistics inputStat = new ImageStatistics(image);
        Iterator<String> images = store.keySet().iterator();
        SortedMap<Double, String> results = new TreeMap<Double, String>(Collections.reverseOrder());
        while (images.hasNext()) {
            String imageName = images.next();
            ImageStatistics stat = new ImageStatistics(store.get(imageName));
            ImageCorrelation correlation = new ImageCorrelation(inputStat, stat);
            if (correlation.getOverallCorr() > 1.8) {
                results.put(correlation.getOverallCorr(), imageName);
            }
        }

        Set<Entry<Double, String>> correlations = results.entrySet();
        Iterator<Entry<Double, String>> iter = correlations.iterator();
        int count = 0;
        while (iter.hasNext() && count < 20) {
            System.out.println(iter.next());
            count++;
        }
    }
}

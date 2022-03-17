package com.villagomezdiaz.common.utilities;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import com.villagomezdiaz.common.tools.DirectoryScanner;
import com.villagomezdiaz.common.tools.ImageCorrelation;
import com.villagomezdiaz.common.tools.ImageStatistics;

public class CompareImagesInFolder {
    public static void compare(String inputFolder, String resultsFile) {

        PrintWriter writer = null;
        ArrayList<String> fNames = new ArrayList<String>();
        HashMap<String, Double> mImages = new HashMap<String, Double>();

        try {
            writer = new PrintWriter(resultsFile, "UTF-8");
            DirectoryScanner dirScanner = new DirectoryScanner(inputFolder, "jpg");
            List<File> list = dirScanner.getResult();
            for (File f : list) {
                fNames.add(f.getAbsolutePath());
                mImages.put(f.getAbsolutePath(), new Double(0));
            }

            for (int i = 0; i < fNames.size(); i++) {
                for (int j = 0; j < fNames.size(); j++) {
                    if (i < j) { // don't compare twice
                        File fileA = new File(fNames.get(i));
                        File fileB = new File(fNames.get(j));

                        BufferedImage imageOne = ImageIO.read(fileA);
                        BufferedImage imageTwo = ImageIO.read(fileB);
                        ImageStatistics stat1 = new ImageStatistics(imageOne);
                        ImageStatistics stat2 = new ImageStatistics(imageTwo);
                        ImageCorrelation corr = new ImageCorrelation(stat1, stat2);
                        writer.println(fileA.getName() + "|" + fileB.getName() + "|" + corr.getOverallCorr());
                        // Double q = (Double) mImages.get(fNames.get(i));
                        // mImages.put(fNames.get(i), new Double(q.doubleValue() +
                        // corr.getOverallCorr()));

                        // q = (Double) mImages.get(fNames.get(j));
                        // mImages.put(fNames.get(j), new Double(q.doubleValue() +
                        // corr.getOverallCorr()));
                    }
                }
            }
            // Iterator<String> keySetIterator = mImages.keySet().iterator();

            // while (keySetIterator.hasNext()) {
            // String key = keySetIterator.next();
            // writer.println(key + "|" + mImages.get(key).doubleValue());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}

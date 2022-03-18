package com.villagomezdiaz.common.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import com.villagomezdiaz.common.Constants;
import com.villagomezdiaz.common.tools.ColorBytesStore;
import com.villagomezdiaz.common.tools.DirectoryScanner;
import com.villagomezdiaz.common.tools.ImageStatistics;

public class SaveSerializedMap {
    public static void process(String inputFolder, String outputFile) {
        try {
            DirectoryScanner scanner = new DirectoryScanner(inputFolder, Constants.IMAGE_FORMAT);
            List<File> list = scanner.getResult();
            ColorBytesStore store = new ColorBytesStore();
            for (File jpg : list) {
                BufferedImage image = ImageIO.read(jpg);
                ImageStatistics stats = new ImageStatistics(image);
                double[][] allColors = stats.getAllColors();
                store.addToStore(jpg.getName(), allColors);
            }
            store.saveToFile(outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("SaveSerializedMap complete");
        }
    }
}

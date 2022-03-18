package com.villagomezdiaz.common.utilities;

import java.io.File;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.villagomezdiaz.common.Constants;
import com.villagomezdiaz.common.tools.BackgroundBlacker;
import com.villagomezdiaz.common.tools.DirectoryScanner;

public class BlackBackgroundToImages {
    public static void process(String inputFolder, String outputFolder, int threshold) {
        try {
            DirectoryScanner scanner = new DirectoryScanner(inputFolder, Constants.IMAGE_FORMAT);
            List<File> list = scanner.getResult();
            for (File f : list) {
                BufferedImage imageIn = ImageIO.read(f);
                BufferedImage imageOut = BackgroundBlacker.convertFromAllSides(imageIn, threshold);

                String outputPath = outputFolder + f.getAbsolutePath().substring(inputFolder.length());
                File output = new File(outputPath);
                File dir = new File(output.getParent());
                dir.mkdirs();
                ImageIO.write(imageOut, Constants.IMAGE_FORMAT, output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("BlackBackgroundToImages complete");
        }
    }
}

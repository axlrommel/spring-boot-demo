package com.villagomezdiaz.app;

import java.io.File;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.villagomezdiaz.common.tools.ColorBytesStore;
import com.villagomezdiaz.common.tools.DirectoryScanner;
import com.villagomezdiaz.common.tools.ImageStatistics;
import com.villagomezdiaz.common.utilities.BackgroundBlacker;
import com.villagomezdiaz.common.utilities.CompareImagesInFolder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// @Bean
	public CommandLineRunner backgroundBlacker() {
		return args -> {
			String pathIn = "/Users/rav0214/Work/what-is-that-bird/images";
			String pathOut = "/Users/rav0214/Work/what-is-that-bird/sampleImagesOutput";
			final int almostBlack = 30;
			try {
				DirectoryScanner scanner = new DirectoryScanner(pathIn, "jpg");
				List<File> list = scanner.getResult();
				for (File f : list) {
					BufferedImage imageIn = ImageIO.read(f);
					BufferedImage imageOut = BackgroundBlacker.convertFromAllSides(imageIn, almostBlack);

					String outputPath = pathOut + f.getAbsolutePath().substring(pathIn.length());
					File output = new File(outputPath);
					File dir = new File(output.getParent());
					dir.mkdirs();
					ImageIO.write(imageOut, "jpg", output);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("Done converting");
			}
		};
	}

	// @Bean
	public CommandLineRunner compareImagesInFolder() {
		return args -> {
			String inputFolder = "/Users/rav0214/Work/what-is-that-bird/sampleImagesOutput";
			String resultsFolder = "/Users/rav0214/Work/what-is-that-bird/compareResults";
			List<File> folders = DirectoryScanner.getSubFolders(inputFolder);
			for (File folder : folders) {
				CompareImagesInFolder.compare(folder.getAbsolutePath(), resultsFolder + "/" + folder.getName());
			}
			System.out.println("done");
		};
	}

	@Bean
	public CommandLineRunner saveBytesToObject() {
		return args -> {
			String inputFolder = "/Users/rav0214/Work/what-is-that-bird/sampleImagesOutput";
			String outputFile = "/Users/rav0214/Work/what-is-that-bird/birds.bin";
			DirectoryScanner scanner = new DirectoryScanner(inputFolder, "jpg");
			List<File> list = scanner.getResult();
			ColorBytesStore store = new ColorBytesStore();
			for (File jpg : list) {
				BufferedImage image = ImageIO.read(jpg);
				ImageStatistics stats = new ImageStatistics(image);
				double[] reds = stats.getReds();
				double[] greens = stats.getGreens();
				double[] blues = stats.getBlues();
				double[][] allColors = new double[][] { reds, greens, blues };
				store.addToStore(jpg.getName(), allColors);
			}
			store.saveToFile(outputFile);
			System.out.println("done");
		};
	}
}

package com.villagomezdiaz.app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.villagomezdiaz.app.storage.StorageProperties;
import com.villagomezdiaz.app.storage.StorageService;
import com.villagomezdiaz.common.tools.BackgroundBlacker;
import com.villagomezdiaz.common.tools.ColorBytesStore;
import com.villagomezdiaz.common.utilities.BlackBackgroundToImages;
import com.villagomezdiaz.common.utilities.FindImageMatches;
import com.villagomezdiaz.common.utilities.SaveSerializedMap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

	// @Bean
	public CommandLineRunner backgroundBlacker() {
		return args -> {
			int threshold = 50;
			String pathIn = "/Users/rav0214/Work/what-is-that-bird/images";
			String pathOut = "/Users/rav0214/Work/what-is-that-bird/imagesOutput/" + threshold;
			BlackBackgroundToImages.process(pathIn, pathOut, threshold);
		};
	}

	// @Bean
	public CommandLineRunner saveBytesToObject() {
		return args -> {
			int threshold = 50;
			String inputFolder = "/Users/rav0214/Work/what-is-that-bird/imagesOutput/" + threshold;
			String outputFile = "/Users/rav0214/Work/what-is-that-bird/birds." + threshold + ".bin";
			SaveSerializedMap.process(inputFolder, outputFile);
		};
	}

	// @Bean
	public CommandLineRunner findBirdMatch() {
		return args -> {
			int threshold = 30;
			String inputImage = "/Users/rav0214/Work/what-is-that-bird/testImages/pilatedWoodpecker.png";
			String binFile = "/Users/rav0214/Work/what-is-that-bird/birds." + threshold + ".bin";
			File imageFile = new File(inputImage);
			BufferedImage image = ImageIO.read(imageFile);
			BufferedImage imageOut = BackgroundBlacker.convertFromAllSides(image, threshold);
			HashMap<String, double[][]> store = ColorBytesStore.getFromFile(binFile);

			FindImageMatches.process(imageOut, store);

		};
	}
}

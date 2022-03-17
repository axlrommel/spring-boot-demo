package com.villagomezdiaz.app;

import java.io.File;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.villagomezdiaz.common.tools.DirectoryScanner;
import com.villagomezdiaz.common.utilities.BackgroundBlacker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			String pathIn = "/Users/rav0214/Work/what-is-that-bird/sampleImages";
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
				// ?
			}

		};
	}

}

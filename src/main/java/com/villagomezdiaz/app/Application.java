package com.villagomezdiaz.app;

import java.io.File;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.villagomezdiaz.common.utilities.BackgroundBlacker;
import com.villagomezdiaz.common.utilities.DirectoryScanner;
import com.villagomezdiaz.jhlabs.ImageUtils;

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
			final int almostBlack = 50;
			try {
				DirectoryScanner dirScanner = new DirectoryScanner();
				 dirScanner.setDirectoryToSearch(pathIn);
				 dirScanner.setFileTypeToSearch("jpg");
				 dirScanner.searchDirectory();
				 List<File> list = dirScanner.getResult();
				 System.out.println("hey");
				 for(File f:list)
				 {
					 String inputPath = f.getAbsolutePath();
					 System.out.println(inputPath);
					 BufferedImage imageIn = ImageIO.read(f);
					 BufferedImage imageTmp = BackgroundBlacker.convertFromCenter(imageIn, almostBlack);
					 
					 int newHeight = (int)(imageIn.getHeight()*2/3);
					 BufferedImage imageOut = ImageUtils.getSubimage(imageTmp, 0, 0, imageIn.getWidth(), newHeight);
					 
					 String outputPath = pathOut + inputPath.substring(pathIn.length());
					 File output = new File(outputPath);
					 String dirPath = output.getParent();
					 File dir = new File(dirPath);
					 dir.mkdirs();
					 ImageIO.write(imageOut, "jpg", output);
				 }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				
			}			

		};
	}

}

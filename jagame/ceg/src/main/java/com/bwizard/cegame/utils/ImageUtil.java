package com.bwizard.cegame.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.bwizard.cegame.tools.FileManager;

/*
 * This class provide functionality for manipulating Images
 * @author Krzysztof Majka
 * @version 1.0
 */
public class ImageUtil {
	
	public static InputStream getData(String file)  {
		
		FileManager fileManager = new FileManager(file);
		InputStream is = fileManager.getInputStream();
		
		return is;
	}
	
	public static BufferedImage loadImage(String file)  {
		
		BufferedImage bi = null;
		try {
				FileManager fileManager = new FileManager(file);
				bi = ImageIO.read(fileManager.getFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return bi;
	}
	
}

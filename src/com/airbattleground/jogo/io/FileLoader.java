package com.airbattleground.jogo.io;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileLoader {
	
	public static BufferedImage readImageFromFile(String imagePath) {
		try {
			BufferedImage img = ImageIO.read(new File("./resources/textures" + imagePath));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String readFileAsString(String filePath) {
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(new File("./resources" + filePath)));
			StringBuilder stringBuilder = new StringBuilder();
			String readLine = fileReader.readLine();
			
			while(readLine != null) {
				stringBuilder.append(readLine);
				stringBuilder.append('\n');
				
				readLine = fileReader.readLine();
			}
			
			fileReader.close();
			
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}

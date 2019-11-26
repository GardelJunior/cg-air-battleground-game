package com.airbattleground.jogo.utils;

public class Utils {
	public static float[] getRGB(char[] characterList, int index) {
		String red = String.valueOf(characterList[index + 2]) + String.valueOf(characterList[index + 3]); 
		String green = String.valueOf(characterList[index + 4]) + String.valueOf(characterList[index + 5]); 
		String blue = String.valueOf(characterList[index + 6]) + String.valueOf(characterList[index + 7]); 
		return new float[] {
			Integer.parseInt(red,16) / 255.0f,
			Integer.parseInt(green,16) / 255.0f,
			Integer.parseInt(blue,16) / 255.0f,
		};
	}
	
	public static float[] getMiniRGB(char[] characterList, int index) {
		String red = String.valueOf(characterList[index + 2]) + String.valueOf(characterList[index + 2]); 
		String green = String.valueOf(characterList[index + 3]) + String.valueOf(characterList[index + 3]); 
		String blue = String.valueOf(characterList[index + 4]) + String.valueOf(characterList[index + 4]); 
		return new float[] {
			Integer.parseInt(red,16) / 255.0f,
			Integer.parseInt(green,16) / 255.0f,
			Integer.parseInt(blue,16) / 255.0f,
		};
	}
}

package com.airbattleground.jogo.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.airbattleground.*;
import com.airbattleground.jogo.manager.*;
import com.airbattleground.jogo.texture.font.*;
import com.airbattleground.jogo.texture.font.Character;

public class FontLoader {

	private static final int KEY_INDEX = 0;
	private static final int VALUE_INDEX = 1;

	public static Font loadFontFromFile(String fontName) {
		String[] fontLines = FileLoader.readFileAsString(fontName).split("\n");
		Font font = new Font();
		font.setAspectRatio((float) Jogo.WIDTH / (float) Jogo.HEIGHT);
		processFontDescription(fontLines, font);
		return font;
	}

	private static void processFontDescription(String[] fontConfigLines, Font font) {
		for (String line : fontConfigLines) {
			if (line.startsWith("info ")) {
				processInfoLine(font, mapLineParams(line, "info "));
			} else if (line.startsWith("common ")) {
				processCommonLine(font, mapLineParams(line, "common "));
			} else if (line.startsWith("page ")) {
				processPageLine(font, mapLineParams(line, "page "));
			} else if (line.startsWith("char ")) {
				processCharLine(font, mapLineParams(line, "char "));
			}
		}
	}

	private static void processInfoLine(Font font, Map<String, String> lineMapping) {
		String[] values = lineMapping.get("padding").split(",");
		font.setFontName(lineMapping.get("face"));
		font.setFontSize(Integer.parseInt(lineMapping.get("size")));
		font.setPadding(new int[] { Integer.parseInt(values[0]), Integer.parseInt(values[1]),
				Integer.parseInt(values[2]), Integer.parseInt(values[3]) });
		font.setPaddingWidth(font.getPadding()[1] + font.getPadding()[3]);
		font.setPaddingHeight(font.getPadding()[0] + font.getPadding()[2]);
	}

	private static void processCommonLine(Font font, Map<String, String> lineMapping) {
		font.setLineHeight(Integer.parseInt(lineMapping.get("lineHeight")));
	}

	private static void processPageLine(Font font, Map<String, String> lineMapping) {
		font.setFontTexture(TextureManager.loadTexture(font.getFontName(), lineMapping.get("file")));
	}

	private static void processCharLine(Font font, Map<String, String> lineMapping) {
		float textureWidth = font.getFontTexture().getWidth();
		float textureHeight = font.getFontTexture().getHeight();

		int id = Integer.parseInt(lineMapping.get("id"));
		float width = Float.parseFloat(lineMapping.get("width"));
		float height = Float.parseFloat(lineMapping.get("height"));
		float xTextureSize = width / textureWidth;
		float yTextureSize = height / textureWidth;
		float xTextureCoord = Float.parseFloat(lineMapping.get("x")) / textureWidth;
		float yTextureCoord = Float.parseFloat(lineMapping.get("y")) / textureHeight;
		float quadWidth = width;
		float quadHeight = height;
		float xOffset = Float.parseFloat(lineMapping.get("xoffset"));
		float yOffset = Float.parseFloat(lineMapping.get("yoffset"));
		float xAdvance = Float.parseFloat(lineMapping.get("xadvance"));

		Character character = new Character(id, xTextureCoord, yTextureCoord, xTextureSize, yTextureSize, xOffset, yOffset, quadWidth, quadHeight, xAdvance);
		font.registerCharacter(character);
	}

	private static Map<String, String> mapLineParams(String line, String startLine) {
		Map<String, String> lineMapping = new HashMap<String, String>();
		Arrays.asList(line.replaceFirst(startLine, "").split(" ")).forEach((keyAndValue) -> {
			String[] keyAndValuePair = keyAndValue.split("=");
			if(keyAndValuePair.length == 1) return;
			keyAndValuePair[VALUE_INDEX] = keyAndValuePair[VALUE_INDEX].replace("\"", ""); // Removes "" from value
			lineMapping.put(keyAndValuePair[KEY_INDEX], keyAndValuePair[VALUE_INDEX]);
		});
		return lineMapping;
	}
}

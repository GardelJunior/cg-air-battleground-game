package com.airbattleground.jogo.manager;

import java.util.HashMap;
import java.util.Map;

import com.airbattleground.jogo.io.*;
import com.airbattleground.jogo.texture.font.*;

public class FontManager {
	
	private static Map<String, Font> fontCollection;
	
	public static void init() {
		fontCollection = new HashMap<String, Font>();
	}
	
	public static Font loadFont(String fontName, String fontPath) {
		if(fontCollection.containsKey(fontName)) {
			return fontCollection.get(fontName);
		}
		Font font = FontLoader.loadFontFromFile("/fonts" + fontPath);
		fontCollection.put(fontName, font);
		return font;
	}
	
	public static Font getFont(String fontName) {
		return fontCollection.get(fontName);
	}
}

package com.airbattleground.jogo.manager;

import static org.lwjgl.opengl.GL11.*;

import java.util.HashMap;
import java.util.Map;

import com.airbattleground.jogo.texture.*;

public class TextureManager {
	private static Map<String, Texture> textureCollection;
	
	public static void init() {
		textureCollection = new HashMap<String, Texture>();
	}
	
	public static Texture loadTexture(String textureName, String textureFile) {
		Texture texture = Texture.Builder.fromFile(textureFile);
		textureCollection.put(textureName, texture);
		return texture;
	}
	
	public static Texture getTexture(String textureName) {
		return textureCollection.get(textureName);
	}
	
	public static void bind(String textureName) {
		textureCollection.get(textureName).bind();
	}
	
	public static void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public static void clear() {
		textureCollection.forEach((name,texture) -> texture.clear());
		textureCollection.clear();
	}
}

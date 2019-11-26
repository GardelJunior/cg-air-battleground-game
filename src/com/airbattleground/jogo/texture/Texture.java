package com.airbattleground.jogo.texture;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import com.airbattleground.jogo.io.*;

public class Texture {

	private int textureID;
	private int width;
	private int height;
	private float xPxScale, yPxScale;

	private Texture() {
	}

	public int getTextureID() {
		return textureID;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getxPxScale() {
		return xPxScale;
	}

	public float getyPxScale() {
		return yPxScale;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, textureID);
	}
	
	public void clear() {
		glBindTexture(GL_TEXTURE_2D, 0);
		glDeleteTextures(textureID);
	}

	public static class Builder {
		
		public static Texture fromFile(String filePath) {
			try{
				return fromBufferedImage(FileLoader.readImageFromFile(filePath));
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public static Texture fromBufferedImage(BufferedImage image) {
			try(MemoryStack stack = MemoryStack.stackPush()){
				
				int pixels[] = new int[image.getWidth() * image.getHeight()];
				image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
				
				boolean hasAlphaChannel = image.getColorModel().hasAlpha();
				int size = (hasAlphaChannel)? 4 : 3;
				
				ByteBuffer buffer = MemoryUtil.memAlloc( pixels.length * size );
				for(int pixel : pixels) {
					// pixel = 8bit Alpha | 8bit Red | 8bit Green | 8bit Blue
					// pixel = aaaaaaaa|rrrrrrrr|gggggggg|bbbbbbbb
					buffer.put((byte) ((pixel >> 16) & 0xff)); //Red
					buffer.put((byte) ((pixel >> 8) & 0xff)); //Green
					buffer.put((byte) ((pixel) & 0xff)); //Blue
					if(hasAlphaChannel) buffer.put((byte) ((pixel >> 24) & 0xff)); //Alpha
				}
				buffer.flip();
				
				Texture texture = fromByteBuffer(buffer, image.getWidth(), image.getHeight(), hasAlphaChannel);
				MemoryUtil.memFree(buffer);
				
				return texture;
			}
		}
		
		public static Texture fromByteBuffer(ByteBuffer buffer, int width, int height, boolean hasAlpha) {
			Texture texture = new Texture();
			glEnable(GL_TEXTURE_2D);
			
			texture.textureID = glGenTextures();
			texture.width = width;
			texture.height = height;
			texture.xPxScale = 1.0f / (float)width;
			texture.yPxScale = 1.0f / (float)height;
			
			glBindTexture(GL_TEXTURE_2D, texture.textureID);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexImage2D(GL_TEXTURE_2D, 0, hasAlpha? GL_RGBA : GL_RGB, width, height, 0, hasAlpha? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, buffer);
			glBindTexture(GL_TEXTURE_2D, 0);
			
			return texture;
		}
	}
}

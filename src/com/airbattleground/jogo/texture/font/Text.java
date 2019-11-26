package com.airbattleground.jogo.texture.font;

import static org.lwjgl.opengl.GL11.*;

public class Text {
	private String text;
	private Font font;
	private float width, height;
	private float red = 1, green = 1, blue = 1, alpha = 1;
	private float x, y;
	private float fontSize;
	private int drawListId;
	private boolean centered;

	public Text(String text,float x, float y,float width, float height, Font font,float fontSize, boolean isCentered) {
		this.text = text;
		this.font = font;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.fontSize = fontSize / (float) font.getFontSize();
		this.centered = isCentered;
		updateChanges();
	}

	public void updateChanges() {
		if(drawListId != 0) clear();
		drawListId = glGenLists(1);
		glNewList(drawListId, GL_COMPILE);
		font.renderText(this);
		glEndList();
	}

	public void render() {
		glPushMatrix();
		glTranslatef(x - ((centered)? width / 2f : 0f), y, 0);
		glCallList(drawListId);
		glPopMatrix();
	}

	public String getText() {
		return text;
	}

	public Text setText(String text) {
		this.text = text;
		return this;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public void setRGB(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public void setRGB(float[] rgb) {
		this.red = rgb[0];
		this.green = rgb[1];
		this.blue = rgb[2];
	}
	
	public void setRGBA(float red, float green, float blue, float alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	public void setRGBA(float[] rgb) {
		this.red = rgb[0];
		this.green = rgb[1];
		this.blue = rgb[2];
		this.alpha = rgb[3];
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}
	
	public void clear() {
		glDeleteLists(drawListId, 1);
	}

}

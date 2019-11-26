package com.airbattleground.jogo.texture.font;

public class Character {

	private int id;
	private float xTextureCoord;
	private float yTextureCoord;
	private float textureWidth;
	private float textureHeight;
	private float xOffset;
	private float yOffset;
	private float sizeX;
	private float sizeY;
	private float xAdvance;

	public Character(int id, float xTextureCoord, float yTextureCoord, float textureCoordWidth,
			float textureCoordHeight, float xOffset, float yOffset, float sizeX, float sizeY, float xAdvance) {
		this.id = id;
		this.xTextureCoord = xTextureCoord;
		this.yTextureCoord = yTextureCoord;
		this.textureWidth = textureCoordWidth;
		this.textureHeight = textureCoordHeight;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.xAdvance = xAdvance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getxTextureCoord() {
		return xTextureCoord;
	}

	public void setxTextureCoord(float xTextureCoord) {
		this.xTextureCoord = xTextureCoord;
	}

	public float getyTextureCoord() {
		return yTextureCoord;
	}

	public void setyTextureCoord(float yTextureCoord) {
		this.yTextureCoord = yTextureCoord;
	}

	public float getTextureWidth() {
		return textureWidth;
	}

	public void setTextureWidth(float textureWidth) {
		this.textureWidth = textureWidth;
	}

	public float getTextureHeight() {
		return textureHeight;
	}

	public void setTextureHeight(float textureHeight) {
		this.textureHeight = textureHeight;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getSizeX() {
		return sizeX;
	}

	public void setSizeX(float sizeX) {
		this.sizeX = sizeX;
	}

	public float getSizeY() {
		return sizeY;
	}

	public void setSizeY(float sizeY) {
		this.sizeY = sizeY;
	}

	public float getxAdvance() {
		return xAdvance;
	}

	public void setxAdvance(float xAdvance) {
		this.xAdvance = xAdvance;
	}

}

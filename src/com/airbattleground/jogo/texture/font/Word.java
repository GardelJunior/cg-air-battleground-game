package com.airbattleground.jogo.texture.font;

import java.util.ArrayList;
import java.util.List;

public class Word {
	private List<Character> characters = new ArrayList<Character>();
	private float width = 0;
	private float fontSize = 0;
	private float red,green,blue;
	
	public Word(float fontSize, float red, float green, float blue) {
		this.fontSize = fontSize;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public void addCharacter(Character character) {
		characters.add(character);
		width += character.getxAdvance() * fontSize;
	}
	
	public float getWordWidth() {
		return width;
	}
	
	public List<Character> getCharacters(){
		return characters;
	}
	
	public boolean isEmpty() {
		return width < 0.001f;
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
}

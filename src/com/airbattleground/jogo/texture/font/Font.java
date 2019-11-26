package com.airbattleground.jogo.texture.font;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.airbattleground.jogo.texture.*;
import com.airbattleground.jogo.utils.*;

public class Font {

	private static final int ASCII_SPACE = 32;

	private Texture fontTexture;
	private String fontName;
	private int fontSize;
	private int lineHeight;
	private Map<Integer, Character> characterMap = new HashMap<Integer, Character>();
	private int[] padding;
	private int paddingWidth;
	private int paddingHeight;
	private float aspectRatio;
	private float verticalPerPixelSize;
	private float horizontalPerPixelSize;

	public Texture getFontTexture() {
		return fontTexture;
	}

	public void setFontTexture(Texture fontTexture) {
		this.fontTexture = fontTexture;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int originalFontSize) {
		this.fontSize = originalFontSize;
	}

	public int getLineHeight() {
		return lineHeight;
	}

	public void setLineHeight(int lineHeight) {
		this.lineHeight = lineHeight;
	}

	public void registerCharacter(Character character) {
		characterMap.put(character.getId(), character);
	}

	public Map<Integer, Character> getCharacterMap() {
		return characterMap;
	}

	public void setCharacterMap(Map<Integer, Character> characterMap) {
		this.characterMap = characterMap;
	}

	public int[] getPadding() {
		return padding;
	}

	public void setPadding(int[] padding) {
		this.padding = padding;
	}

	public int getPaddingWidth() {
		return paddingWidth;
	}

	public void setPaddingWidth(int paddingWidth) {
		this.paddingWidth = paddingWidth;
	}

	public int getPaddingHeight() {
		return paddingHeight;
	}

	public void setPaddingHeight(int paddingHeight) {
		this.paddingHeight = paddingHeight;
	}

	public float getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public float getVerticalPerPixelSize() {
		return verticalPerPixelSize;
	}

	public void setVerticalPerPixelSize(float verticalPerPixelSize) {
		this.verticalPerPixelSize = verticalPerPixelSize;
	}

	public float getHorizontalPerPixelSize() {
		return horizontalPerPixelSize;
	}

	public void setHorizontalPerPixelSize(float horizontalPerPixelSize) {
		this.horizontalPerPixelSize = horizontalPerPixelSize;
	}

	public void renderText(Text text) {
		fontTexture.bind();
		List<Line> lines = convertTextToLines(text);
		float cursorX = 0;
		float cursorY = 0;
		float fontSizeScale = text.getFontSize();
		glBegin(GL_QUADS);
		for (Line line : lines) {
			if(text.isCentered()) {
				cursorX = (line.getMaxWidth() - line.getCurrentWidth())/2.0f;
			}
			for(Word word : line.getWords()) {
				glColor3f(word.getRed(), word.getGreen(), word.getBlue());
				for(Character character : word.getCharacters()) {
					float x = cursorX + (character.getxOffset() * fontSizeScale);
					float y = cursorY + (character.getyOffset() * fontSizeScale);
					float xMax = x + character.getSizeX() * fontSizeScale;
					float yMax = y + character.getSizeY() * fontSizeScale;
			        
			        glTexCoord2f(character.getxTextureCoord(), character.getyTextureCoord());
					glVertex2f(x, y);
					glTexCoord2f(character.getxTextureCoord(), character.getyTextureCoord() + character.getTextureHeight());
					glVertex2f(x, yMax);
					glTexCoord2f(character.getxTextureCoord() + character.getTextureWidth(), character.getyTextureCoord() + character.getTextureHeight());
					glVertex2f(xMax, yMax);
					glTexCoord2f(character.getxTextureCoord() + character.getTextureWidth(), character.getyTextureCoord());
					glVertex2f(xMax, y);
					
					cursorX += character.getxAdvance() * text.getFontSize();
				}
			}
			cursorX = 0;
			cursorY += lineHeight * text.getFontSize();
		}
		glEnd();
	}

	private List<Line> convertTextToLines(Text text) {
		List<Line> lines = new ArrayList<Line>();
		Line currentLine = new Line(text.getWidth());
		Word currentWord = new Word(text.getFontSize(),text.getRed(),text.getGreen(),text.getBlue());
		char[] characterList = text.getText().toCharArray();

		for (int i = 0 ; i < characterList.length ; i++) {
			int currentChar = characterList[i];
			if (currentChar == ASCII_SPACE) {
				currentLine = insertWordOnLine(currentWord, currentLine, lines, text.getWidth());
				currentWord = new Word(text.getFontSize(),text.getRed(),text.getGreen(),text.getBlue());
			} else if(currentChar == '<' && i + 1 < characterList.length && characterList[i + 1] == '#') {
				if(i + 8 < characterList.length && characterList[i + 8] == '>') {
					text.setRGB(Utils.getRGB(characterList, i));
					currentLine = insertWordOnLine(currentWord, currentLine, lines, text.getWidth());
					currentWord = new Word(text.getFontSize(), text.getRed(), text.getGreen(), text.getBlue());
					i += 8;
					continue;
				}else if(i + 5 < characterList.length && characterList[i + 5] == '>') {
					text.setRGB(Utils.getMiniRGB(characterList, i));
					currentLine = insertWordOnLine(currentWord, currentLine, lines, text.getWidth());
					currentWord = new Word(text.getFontSize(), text.getRed(), text.getGreen(), text.getBlue());
					i += 5;
					continue;
				} else if(i + 2 < characterList.length && characterList[i + 2] == '>') {
					text.setRGB(1, 1, 1);
					currentLine = insertWordOnLine(currentWord, currentLine, lines, text.getWidth());
					currentWord = new Word(text.getFontSize(), text.getRed(), text.getGreen(), text.getBlue());
					i += 2;
					continue;
				}
			} else if(currentChar == '\n') {
				if(!currentLine.attempToAddWord(currentWord)) {
					lines.add(currentLine);
					currentLine = new Line(text.getWidth());
					currentLine.attempToAddWord(currentWord);
				}else {
					lines.add(currentLine);
					currentLine = new Line(text.getWidth());
				}
				currentWord = new Word(text.getFontSize(),text.getRed(),text.getGreen(),text.getBlue());
				continue;
			}
			Character character = getCharacterMap().get(currentChar);
			currentWord.addCharacter(character);
		}
		if(!currentWord.isEmpty()) {
			currentLine.attempToAddWord(currentWord);
		}
		lines.add(currentLine);
		return lines;
	}
	
	private Line insertWordOnLine(Word currentWord, Line currentLine, List<Line> lines, float width) {
		if (!currentLine.attempToAddWord(currentWord)) {
			lines.add(currentLine);
			currentLine = new Line(width);
			currentLine.attempToAddWord(currentWord);
		}
		return currentLine;
	}
}

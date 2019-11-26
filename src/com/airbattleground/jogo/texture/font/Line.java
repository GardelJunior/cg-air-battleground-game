package com.airbattleground.jogo.texture.font;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private float maxWidth;
	private float currentWidth = 0;
	private List<Word> words = new ArrayList<Word>();
	
	public Line(float maxWidth) {
		this.maxWidth = maxWidth;
	}
	
	public boolean attempToAddWord(Word word) {
		if(currentWidth + word.getWordWidth() < maxWidth) {
			words.add(word);
			currentWidth += word.getWordWidth();
			return true;
		}else {
			return false;
		}
	}

	public float getMaxWidth() {
		return maxWidth;
	}

	public float getCurrentWidth() {
		return currentWidth;
	}

	public List<Word> getWords() {
		return words;
	}
}

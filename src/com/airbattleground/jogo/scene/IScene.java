package com.airbattleground.jogo.scene;

public interface IScene {
	public void init();
	public void update();
	public void render();
	public void onKeyEvent(int key, int action);
	public void end();
}

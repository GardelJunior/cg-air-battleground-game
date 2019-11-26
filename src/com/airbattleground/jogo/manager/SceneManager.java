package com.airbattleground.jogo.manager;

import java.util.HashMap;
import java.util.Map;

import com.airbattleground.jogo.scene.*;

public class SceneManager {
	private Map<String, IScene> sceneCollection = new HashMap<String,IScene>();
	private IScene currentScene;
	
	private static SceneManager instance;

	public static SceneManager getInstance() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}
	
	private SceneManager() {}
	
	public void registerScene(String sceneName, IScene scene) {
		sceneCollection.put(sceneName, scene);
	}
	
	public void setCurrentScene(String sceneName, boolean initScene) {
		currentScene = sceneCollection.get(sceneName);
		if(initScene) currentScene.init();
	}
	
	public IScene getCurrentScene() {
		return currentScene;
	}
	
	public void update() {
		currentScene.update();
	}
	
	public void render() {
		currentScene.render();
	}
	
	public void onKeyEvent(int key, int action) {
		currentScene.onKeyEvent(key, action);
	}
	
	public void end() {
		sceneCollection.forEach((sceneName, scene) -> scene.end());
	}
}

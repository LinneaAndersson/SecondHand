package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import android.view.KeyEvent;

import com.secondhand.scene.GamePlayScene;
import com.secondhand.scene.MainMenuScene;
import com.secondhand.scene.SettingsMenuScene;

public class SceneManager {
	
	private static SceneManager instance;
	
	private AllScenes currentSceneEnum;
	
	private Engine engine;
	
	private IGameScene loadingScene, mainMenuScene, settingsMenuScene, gamePlayScene;

	public enum AllScenes {
		LOADING_SCENE, MAIN_MENU_SCENE, SETTINGS_MENU_SCENE, GAME_PLAY_SCENE
	}	
	
	public static SceneManager getInstance() {
		if(instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}
	
	private SceneManager() { }
	
	/**
	 * Setup this singelton class for usage.
	 * */
	public void initialize(final Engine engine) {
		this.engine = engine;
		
		loadingScene = new LoadingScene(this.engine.getCamera());
		mainMenuScene = new MainMenuScene(this.engine.getCamera());
		this.settingsMenuScene = new SettingsMenuScene(this.engine.getCamera());
		this.gamePlayScene = new GamePlayScene(this.engine.getCamera());
	}

	public AllScenes getCurrentSceneEnum() {
		return this.currentSceneEnum;
	}
	
	public IGameScene getCurrentScene() {
		return getScene(currentSceneEnum);
	}
	
	public IGameScene getScene(AllScenes sceneEnum) {
		IGameScene scene = null;

		// also change the scene in the game:
		if (sceneEnum ==  AllScenes.LOADING_SCENE) {
			scene = this.loadingScene;
		} else if (sceneEnum == AllScenes.MAIN_MENU_SCENE) {
			scene = this.mainMenuScene;
		}else if (sceneEnum == AllScenes.SETTINGS_MENU_SCENE) {
			scene = this.settingsMenuScene;
		}else if (sceneEnum == AllScenes.GAME_PLAY_SCENE) {
			scene = this.gamePlayScene;
		}
		
		return scene;
	}

	public IGameScene setCurrentSceneEnum(AllScenes currentSceneEnum) {
		this.currentSceneEnum = currentSceneEnum;
		
		IGameScene currentScene = getCurrentScene();
			
		if (this.currentSceneEnum ==  AllScenes.LOADING_SCENE) {
			currentScene.loadResources();
		}
		
		// fully clear the scene before loading and then load it.
		currentScene.getScene().detachChildren();
		currentScene.loadScene();
		
		this.engine.setScene(currentScene.getScene());
		
		return currentScene;
	}
	
	public void loadAllResources() {
		this.mainMenuScene.loadResources();
		this.settingsMenuScene.loadResources();
		this.gamePlayScene.loadResources();
	}
	
	public boolean sendOnKeyDownToCurrentScene(final int pKeyCode, final KeyEvent pEvent) {
		IGameScene currentScene = getCurrentScene();
		return currentScene.onKeyDown(pKeyCode, pEvent);
	}

}

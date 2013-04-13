package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import android.view.KeyEvent;

import com.secondhand.scene.GamePlayScene;
import com.secondhand.scene.HighScoreScene;
import com.secondhand.scene.MainMenuScene;
import com.secondhand.scene.SettingsMenuScene;

/**
 * This manages all the scenes, is used to set the current scene,
 *  and sends keyboard input from the MainActvity to the current scene.
 *  So it's basically the controller of this app.
 */
public class SceneManager {
	
	private static SceneManager instance;
	
	private AllScenes currentSceneEnum;
	
	private Engine engine;
	
	private IGameScene loadingScene, mainMenuScene, settingsMenuScene, gamePlayScene, highScoreScene;

	// IMPORTANT: when you want to add a new scene to the app, you MUST assign it
	// a corresponding enum value.
	public enum AllScenes {
		LOADING_SCENE, MAIN_MENU_SCENE, SETTINGS_MENU_SCENE, GAME_PLAY_SCENE, HIGH_SCORE_SCENE
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
		
		// IMPORTANT: when you want to add a new scene to the app, it's constructor MUST be called here.
		loadingScene = new LoadingScene(this.engine.getCamera());
		mainMenuScene = new MainMenuScene(this.engine.getCamera());
		this.settingsMenuScene = new SettingsMenuScene(this.engine.getCamera());
		this.gamePlayScene = new GamePlayScene(this.engine.getCamera());
		this.highScoreScene = new HighScoreScene(this.engine.getCamera());
		
	}

	public AllScenes getCurrentSceneEnum() {
		return this.currentSceneEnum;
	}
	
	public IGameScene getCurrentScene() {
		return getScene(currentSceneEnum);
	}
	
	public IGameScene getScene(AllScenes sceneEnum) {
		IGameScene scene = null;
		
		// IMPORTANT: when you want to add a new scene to the app, you MUST make sure
		// that it can be accessed be using this method.

		// also change the scene in the game:
		if (sceneEnum ==  AllScenes.LOADING_SCENE) {
			scene = this.loadingScene;
		} else if (sceneEnum == AllScenes.MAIN_MENU_SCENE) {
			scene = this.mainMenuScene;
		}else if (sceneEnum == AllScenes.SETTINGS_MENU_SCENE) {
			scene = this.settingsMenuScene;
		}else if (sceneEnum == AllScenes.GAME_PLAY_SCENE) {
			scene = this.gamePlayScene;
		}else if (sceneEnum == AllScenes.HIGH_SCORE_SCENE) {
			scene = this.highScoreScene;
		}
		
		return scene;
	}

	public IGameScene setCurrentSceneEnum(AllScenes currentSceneEnum) {
		this.currentSceneEnum = currentSceneEnum;
		
		IGameScene currentScene = getCurrentScene();
			
		// the loading scene is a special case. It handles the loading of all the
		// other scene's resources, so its(the loading scenes) resources must be loaded here.
		if (this.currentSceneEnum ==  AllScenes.LOADING_SCENE) {
			currentScene.loadResources();
		}
		
		// fully clear the scene before loading and then load it.
		currentScene.getScene().detachChildren();
		currentScene.loadScene();
		
		this.engine.setScene(currentScene.getScene());
		
		return currentScene;
	}
	
	// used by the loading scene to load all game resources.
	public void loadAllResources() {
		// IMPORTANT: when you want to add a new scene to the app, you MUST
		// load its resources here.
		this.mainMenuScene.loadResources();
		this.settingsMenuScene.loadResources();
		this.gamePlayScene.loadResources();
		this.highScoreScene.loadResources();
	}
	
	// called from MainActivity.
	public boolean sendOnKeyDownToCurrentScene(final int pKeyCode, final KeyEvent pEvent) {
		IGameScene currentScene = getCurrentScene();
		return currentScene.onKeyDown(pKeyCode, pEvent);
	}

}

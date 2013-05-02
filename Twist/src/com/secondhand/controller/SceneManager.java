package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import android.content.Context;
import android.view.KeyEvent;

import com.secondhand.debug.MyDebug;
import com.secondhand.view.resource.Sounds;
import com.secondhand.view.resource.TextureRegions;
import com.secondhand.view.scene.AllScenes;
import com.secondhand.view.scene.GamePlayScene;
import com.secondhand.view.scene.HighScoreScene;
import com.secondhand.view.scene.IGameScene;
import com.secondhand.view.scene.LoadingScene;
import com.secondhand.view.scene.MainMenuScene;
import com.secondhand.view.scene.SettingsMenuScene;

/**
 * Is used to switch between different scenes. 
 */
public final class SceneManager {

	//private static SceneManager instance;

	private AllScenes currentSceneEnum;

	private Engine engine;
	private Context context;
		private IGameScene loadingScene, mainMenuScene, settingsMenuScene,
			highScoreScene;

	private GamePlayScene gamePlayScene;

	public SceneManager(final Engine engine, final Context context) {
		this.engine = engine;
		this.context = context;
		
		// create all the scenes. 
		this.loadingScene = new LoadingScene(this.engine, context);
		this.mainMenuScene = new MainMenuScene(this.engine, context);
		this.settingsMenuScene = new SettingsMenuScene(this.engine, context);
		this.gamePlayScene = new GamePlayScene(this.engine, context);
		this.highScoreScene = new HighScoreScene(this.engine, context);
	}

	public IGameScene getCurrentScene() {
		return getScene(currentSceneEnum);
	}

	public IGameScene getScene(final AllScenes sceneEnum) {
		IGameScene scene = null;

		// IMPORTANT: when you want to add a new scene to the app, you MUST make
		// sure
		// that it can be accessed be using this method.

		// also change the scene in the game:
		if (sceneEnum == AllScenes.LOADING_SCENE) {
			scene = this.loadingScene;
		} else if (sceneEnum == AllScenes.MAIN_MENU_SCENE) {
			scene = this.mainMenuScene;
		} else if (sceneEnum == AllScenes.SETTINGS_MENU_SCENE) {
			scene = this.settingsMenuScene;
		} else if (sceneEnum == AllScenes.GAME_PLAY_SCENE) {
			scene = this.gamePlayScene;
		} else if (sceneEnum == AllScenes.HIGH_SCORE_SCENE) {
			scene = this.highScoreScene;
		}

		return scene;
	}

	public IGameScene setCurrentSceneEnum(final AllScenes currentSceneEnum) {
		this.currentSceneEnum = currentSceneEnum;
		final IGameScene currentScene = getCurrentScene();	
		
		if(!currentScene.isLoaded()) {;
			MyDebug.d("scene not preloaded, loading!");
			currentScene.loadScene();
		}
		
		if (this.currentSceneEnum == AllScenes.GAME_PLAY_SCENE) {
			new GamePlaySceneController(this.gamePlayScene);
		} else if(this.currentSceneEnum == AllScenes.MAIN_MENU_SCENE) {
			
		}

		this.engine.setScene(currentScene.getScene());

		return currentScene;
	}
	
	// used by the loading scene to load all game resources.
	public void loadAllResources() {
		
		TextureRegions.getInstance().load();
		Sounds.getInstance().load();
		//setIsGameLoaded(true);
	}

	
	public void switchScene(AllScenes scene) {
		this.getCurrentScene().unloadScene();
		this.getCurrentScene().onSwitchScene();
		setCurrentSceneEnum(scene );
	}
	
}

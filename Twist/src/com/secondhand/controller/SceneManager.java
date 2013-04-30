package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import android.content.Context;
import android.view.KeyEvent;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.resource.Sounds;
import com.secondhand.resource.TextureRegions;
import com.secondhand.scene.ChangeLevelScene;
import com.secondhand.scene.GameOverScene;
import com.secondhand.scene.GamePlayScene;
import com.secondhand.scene.HighScoreScene;
import com.secondhand.scene.IGameScene;
import com.secondhand.scene.IGameScene.AllScenes;
import com.secondhand.scene.MainMenuScene;
import com.secondhand.scene.SettingsMenuScene;

/**
 * This manages all the scenes, is used to set the current scene, and sends
 * keyboard input from the MainActvity to the current scene. So it's basically
 * the controller of this app.
 */
public final class SceneManager {

	private static SceneManager instance;

	private AllScenes currentSceneEnum;

	private Engine engine;
	private Context context;
	
	private IGameScene loadingScene, mainMenuScene, settingsMenuScene,
			highScoreScene, gamePlaySceneLoadingScene, changeLevelScene;
	
	
	private GamePlayScene gamePlayScene;
	private GameOverScene gameOverScene;
	
	public GamePlayScene getGamePlayScene() {
		return this.gamePlayScene;
	}

	private GamePlaySceneController gamePlaySceneController;
	
	private boolean isGameLoaded = false;
	
	public void setIsGameLoaded(final boolean isGameLoaded) {
		this.isGameLoaded = isGameLoaded;
	}
	
	public boolean isGameLoaded() {
		return this.isGameLoaded;
	}

	public static SceneManager getInstance() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}
	
	public Context getContext() {
		return this.context;
	}

	private SceneManager() {
	}

	/**
	 * Setup this singelton class for usage.
	 * */
	public void initialize(final Engine engine, final Context context) {
		this.engine = engine;
		this.context = context;
		this.loadingScene = new LoadingScene(this.engine, context);
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
		} else if (sceneEnum == AllScenes.GAME_OVER_SCENE) {
			scene = this.gameOverScene;
		}else if (sceneEnum == AllScenes.GAME_PLAY_SCENE_LOADING_SCENE) {
			scene = this.gamePlaySceneLoadingScene;
		}else if(sceneEnum == AllScenes.CHANGE_LEVEL_SCENE){
			scene = this.changeLevelScene;
		}

		return scene;
	}

	
	public void preloadSceneEnum(final AllScenes scene) {
		
	}
	
	public IGameScene setCurrentSceneEnum(final AllScenes currentSceneEnum) {
		this.currentSceneEnum = currentSceneEnum;
		final IGameScene currentScene = getCurrentScene();	
		MyDebug.d("currentScene.getScene()==null =" + currentScene.getScene());
		
		if(!currentScene.isLoaded()) {;
			MyDebug.d("scene not preloaded, loading!");
			currentScene.loadScene();
		}
		
		if (this.currentSceneEnum == AllScenes.GAME_PLAY_SCENE) {
			MyDebug.d("kommer den in i 2" + currentScene.getScene());
			gamePlaySceneController = new GamePlaySceneController(this.gamePlayScene);
		}

		this.engine.setScene(currentScene.getScene());

		return currentScene;
	}
	
	// used by the loading scene to load all game resources.
	public void loadAllResources() {
		
		TextureRegions.getInstance().load();
		Sounds.getInstance().load();
		
		// IMPORTANT: when you want to add a new scene to the app, it's
		// constructor MUST be called here.
		this.mainMenuScene = new MainMenuScene(this.engine, context);
		this.settingsMenuScene = new SettingsMenuScene(this.engine, context);
		this.gameOverScene = new GameOverScene(this.engine, context);
		this.gamePlayScene = new GamePlayScene(this.engine, context);
		this.gamePlaySceneLoadingScene = new GamePlaySceneLoadingScene(this.engine, context);
		this.highScoreScene = new HighScoreScene(this.engine, context);
		this.changeLevelScene = new ChangeLevelScene(this.engine , context, gamePlayScene.getGameWorld());
		
		
	}

	// called from MainActivity.
	public boolean sendOnKeyDownToCurrentScene(final int pKeyCode,
			final KeyEvent pEvent) {
		final IGameScene currentScene = getCurrentScene();
		return currentScene.onKeyDown(pKeyCode, pEvent);
	}

}

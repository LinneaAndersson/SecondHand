package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import android.content.Context;
import android.view.KeyEvent;

import com.secondhand.resource.Sounds;
import com.secondhand.resource.TextureRegions;
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
			highScoreScene;
	private GamePlayScene gamePlayScene;
	private GameOverScene gameOverScene;

	private GamePlaySceneController gamePlaySceneController;

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
		}

		return scene;
	}

	public IGameScene setCurrentSceneEnum(final AllScenes currentSceneEnum) {
		this.currentSceneEnum = currentSceneEnum;

		final IGameScene currentScene = getCurrentScene();	
		
		if (this.currentSceneEnum == AllScenes.GAME_PLAY_SCENE && gamePlaySceneController==null) {
			gamePlaySceneController = new GamePlaySceneController(this.gamePlayScene);
		}
		// fully clear the scene before loading and then load it.
		currentScene.getScene().detachChildren();
		currentScene.loadScene();
		
		
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
		
		// I do believe this belong here
		gamePlayScene.getGameWorld().getPhysicsWorld().setContactListener(
				new CollisionContactListener(gamePlayScene.getGameWorld()));
		
		this.gameOverScene.setGameWorld(gamePlayScene.getGameWorld());

		this.highScoreScene = new HighScoreScene(this.engine, context);
	}

	// called from MainActivity.
	public boolean sendOnKeyDownToCurrentScene(final int pKeyCode,
			final KeyEvent pEvent) {
		final IGameScene currentScene = getCurrentScene();
		return currentScene.onKeyDown(pKeyCode, pEvent);
	}

}

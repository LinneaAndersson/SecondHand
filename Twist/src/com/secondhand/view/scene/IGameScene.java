package com.secondhand.view.scene;

import org.anddev.andengine.entity.scene.Scene;

import android.view.KeyEvent;

/**
 * All scenes in the game should implement this interface(but you will in most
 * cases want to extend GameScene or GameMenuScene instead, since they are more
 * convenient)
 */
public interface IGameScene {

	// IMPORTANT: when you want to add a new scene to the app, you MUST assign
	// it
	// a corresponding enum value.
	public enum AllScenes {
		LOADING_SCENE, MAIN_MENU_SCENE, SETTINGS_MENU_SCENE, GAME_PLAY_SCENE, HIGH_SCORE_SCENE,
GAME_PLAY_SCENE_LOADING_SCENE, CHANGE_LEVEL_SCENE
	}
	
	/**
	 * Load and setup the scene for viewing(this basically means that all the
	 * child entities of the scene will be attached using attachChild)
	 */
	void loadScene();
	
	void unloadScene();

	Scene getScene();
	
	void onSwitchScene();
	
	/**
	 * Override this method if you want to get keyboard input from the physical
	 * buttons of an Android device.
	 */
	//boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent);

	// Returns the parent-scene, for example SettingsMenuScene returns
	// AllScenes.MAIN_MENU_SCENE
	AllScenes getParentScene();
	
	void setScene(AllScenes sceneEnum);
	
	boolean isLoaded();
}

	
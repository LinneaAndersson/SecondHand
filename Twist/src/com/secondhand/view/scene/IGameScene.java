package com.secondhand.view.scene;

import org.anddev.andengine.entity.scene.Scene;


/**
 * All scenes in the game should implement this interface(but you will in most
 * cases want to extend GameScene or GameMenuScene instead, since they are more
 * convenient)
 */
public interface IGameScene {
	/**
	 * Load and setup the scene for viewing(this basically means that all the
	 * child entities of the scene will be attached using attachChild)
	 */
	void loadScene();
	
	void unloadScene();

	Scene getScene();
	
	void onSwitchScene();
	
	// Returns the parent-scene, for example SettingsMenuScene returns
	// AllScenes.MAIN_MENU_SCENE
	AllScenes getParentScene();
	
	boolean isLoaded();
}

	
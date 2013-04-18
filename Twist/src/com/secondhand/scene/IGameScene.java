package com.secondhand.scene;


import org.anddev.andengine.entity.scene.Scene;

import com.secondhand.controller.SceneManager;
import com.secondhand.controller.SceneManager.AllScenes;

import android.view.KeyEvent;

/**
 * All scenes in the game should implement this interface(but you will in most cases want to
 * extend GameScene or GameMenuScene instead, since they are more convenient)
 */
public interface IGameScene {
    
	/**
	 * Load the resources required by the scene.
	 */
	void loadResources();

	/**
	 * Load and setup the scene for viewing(this basically means that all the child entities of 
	 * the scene will be attached using attachChild)
	 */
    void loadScene();
    
    
    Scene getScene();

    /**
     * Override this method if you want to get keyboard input from the physical buttons
     * of an Android device.
     */
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent);
	
	// Returns the parent-scene, for example SettingsMenuScene returns AllScenes.MAIN_MENU_SCENE
	public AllScenes getParentScene();
}
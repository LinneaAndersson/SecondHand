package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

import com.secondhand.view.scene.AllScenes;

import android.content.Context;
import android.view.KeyEvent;

/**
 * The controller for all the scenes. While the SceneManager is capable of switching between scenes,
 * this is the one who does the actual switching
 */
public class SceneController {

	private Engine engine;
	private Context context;
	
	private SceneManager sceneManager;
	
	public SceneController(final Engine engine, final Context context) {
		this.engine = engine;
		this.context = context;
		this.sceneManager = new SceneManager(engine, context);	
		
		// now register controllers for all the scenes. 
	}
	
	private boolean isGameLoaded = false;
	
	public void setIsGameLoaded(final boolean isGameLoaded) {
		this.isGameLoaded = isGameLoaded;
	}
	
	public boolean isGameLoaded() {
		return this.isGameLoaded;
	}
	
	public void switchScene(AllScenes scene) {
		this.sceneManager.switchScene(scene);
	}
	
	public Scene getCurrentScene() {
		return this.sceneManager.getCurrentScene().getScene();
	}
	
	// called from MainActivity.
	public boolean sendOnKeyDownToCurrentScene(final int pKeyCode,
			final KeyEvent pEvent) {
		
		if (pKeyCode == KeyEvent.KEYCODE_BACK
				&& pEvent.getAction() == KeyEvent.ACTION_DOWN) {
			
			if(this.sceneManager.getCurrentScene().getParentScene() != null) {

				this.switchScene(this.sceneManager.getCurrentScene().getParentScene());
				
				return true;
			} else {
				return false;
			}
		} else
			return false;
	}
}

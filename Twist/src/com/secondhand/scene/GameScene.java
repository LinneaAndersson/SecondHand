package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.entity.scene.Scene;

import android.content.Context;
import android.view.KeyEvent;

import com.secondhand.controller.MySmoothCamera;
import com.secondhand.controller.SceneManager;

/**
 * Credit for the main idea behind this class goes to:
 * http://andengine.wikidot.com
 * /loading-resources-in-the-background-with-a-loading-screen
 */
public abstract class GameScene extends Scene implements IGameScene {

	protected final MySmoothCamera smoothCamera;
	protected final Engine engine;
	protected final Context context;
	protected boolean isLoaded;

	public GameScene(final Engine engine, final Context context) {
		super();
		isLoaded = false;
		this.smoothCamera = (MySmoothCamera)engine.getCamera();
		this.engine = engine;
		this.context = context;
	}
	
	@Override
	public void loadScene() {
		super.detachChildren();
		isLoaded = true;
	}
	
	public boolean isLoaded() {
		return this.isLoaded;
	}

	@Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		if (pKeyCode == KeyEvent.KEYCODE_BACK
				&& pEvent.getAction() == KeyEvent.ACTION_DOWN) {
			final AllScenes parent = getParentScene();
			if (parent != null) {
//				// it should be loaded the next time it's shown. 
				isLoaded = false;
				setScene(parent);

				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public Scene getScene() {
		return this;
	}
	
	public void setScene(final AllScenes sceneEnum){
		SceneManager.getInstance().setCurrentSceneEnum(sceneEnum);
	}
}

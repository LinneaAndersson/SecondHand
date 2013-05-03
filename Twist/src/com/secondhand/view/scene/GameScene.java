package com.secondhand.view.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.entity.scene.Scene;

import android.content.Context;

/**
 * Credit for the main idea behind this class goes to:
 * http://andengine.wikidot.com
 * /loading-resources-in-the-background-with-a-loading-screen
 */
public abstract class GameScene extends Scene implements IGameScene {

	protected final SmoothCamera smoothCamera;
	protected final Engine engine;
	protected final Context context;
	private boolean isLoaded;
	
	public GameScene(final Engine engine, final Context context) {
		super();
		this.smoothCamera = (SmoothCamera)engine.getCamera();
		this.engine = engine;
		this.context = context;
	}
	
	@Override
	public void loadScene() {
		this.detachChildren();
		this.isLoaded = true;
	}
	
	public boolean isLoaded() {
		return this.isLoaded;
	}

	@Override
	public Scene getScene() {
		return this;
	}
	
	@Override
	public void onSwitchScene() { this.isLoaded = false; }
}

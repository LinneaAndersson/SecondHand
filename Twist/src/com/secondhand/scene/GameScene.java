package com.secondhand.scene;


import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;

import com.secondhand.controller.IGameScene;

import android.content.Context;
import android.view.KeyEvent;

/**
 * Credit for the main idea behind this class goes to: 
 * http://andengine.wikidot.com/loading-resources-in-the-background-with-a-loading-screen
 */
public abstract class GameScene extends Scene implements IGameScene {

	protected final Camera camera;
	protected final Engine engine;
	protected final Context context;
	
    public GameScene(Engine engine, Context context) {
        super();
        this.camera = engine.getCamera();
        this.engine = engine;
        this.context = context;
    }

    @Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		return false;
	}

	@Override
	public Scene getScene() {
		return this;
	}
}

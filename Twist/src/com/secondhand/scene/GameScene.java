package com.secondhand.scene;


import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;

import com.secondhand.controller.IGameScene;

import android.view.KeyEvent;

/**
 * Credit for the main idea behind this class goes to: 
 * http://andengine.wikidot.com/loading-resources-in-the-background-with-a-loading-screen
 */
public abstract class GameScene extends Scene implements IGameScene {

	protected final Camera camera;
	
    public GameScene(Camera camera) {
        super();
        this.camera = camera;
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

package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import android.content.Context;

import com.secondhand.resource.Fonts;
import com.secondhand.scene.GameScene;
import com.secondhand.scene.IGameScene;
import com.secondhand.scene.IGameScene.AllScenes;

public class GameLevelLoadingScene extends GameScene{

	public GameLevelLoadingScene(final Engine engine, final Context context) {
		super(engine, context);
	}

	@Override
	public void loadScene() {
		// add loading text
		this.attachChild(new LoadingText(this.smoothCamera));

		final IAsyncCallback callback = new IAsyncCallback() {

			@Override
			public void work() {
				// preload gameplay scene 
				
			}

			@Override
			public void onWorkComplete() {
				// now go to game play scene.

			}
		};

		new AsyncTaskGameLoader().execute(callback);
	}

	@Override
	public AllScenes getParentScene() {
		return null;
	}
}

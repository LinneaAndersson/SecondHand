package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import android.content.Context;

import com.secondhand.debug.MyDebug;
import com.secondhand.resource.LocalizationStrings;
import com.secondhand.scene.GameScene;

public class GamePlaySceneLoadingScene extends GameScene{

	public GamePlaySceneLoadingScene(final Engine engine, final Context context) {
		super(engine, context);
	}

	@Override
	public void loadScene() {
		super.detachChildren();
		

		MyDebug.d("setting up level loading scene");
		
		// add loading text
		this.attachChild( 
				new LoadingText(
						LocalizationStrings.getInstance().getLocalizedString("loading_level"),
						 this.smoothCamera));	

		final IAsyncCallback callback = new IAsyncCallback() {

			@Override
			public void work() {
				// preload gameplay scene 
				SceneManager.getInstance().getGamePlayScene().loadScene();
			}

			@Override
			public void onWorkComplete() {
				// now go to game play scene.
				SceneManager.getInstance().setCurrentSceneEnum(AllScenes.GAME_PLAY_SCENE);
				MyDebug.d("done with loading level!");
				
			}
		};

		new AsyncTaskGameLoader().execute(callback);
	}

	@Override
	public AllScenes getParentScene() {
		return null;
	}
}

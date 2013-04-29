package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import android.content.Context;

import com.secondhand.resource.Fonts;
import com.secondhand.scene.GameScene;
import com.secondhand.scene.IGameScene;

public class LoadingScene extends GameScene {

	public LoadingScene(final Engine engine, final Context context) {
		super(engine, context);
		Fonts.getInstance().load();	
	}

	@Override
	public void loadScene() {
		SceneManager.getInstance().setIsGameLoaded(true);

		// add loading text
		this.attachChild(new LoadingText(this.smoothCamera));

		// in the loading scene we will load all the resources of all the
		// scenes.

		// this is simply done by using a background loading thread:
		// (this allows us to use an animated loading screen)

		final IAsyncCallback callback = new IAsyncCallback() {

			@Override
			public void work() {
				// load all resources of all scenes.
				SceneManager.getInstance().loadAllResources();
			}

			@Override
			public void onWorkComplete() {
				
				// see if this makes the textures load. 
				//LoadingScene.this.engine.getTextureManager().reloadTextures();

				// go to main menu once the loading is done.
				SceneManager.getInstance().setCurrentSceneEnum(
						IGameScene.AllScenes.MAIN_MENU_SCENE);

			}
		};

		new AsyncTaskGameLoader().execute(callback);
	}

	@Override
	public AllScenes getParentScene() {
		// If null is returned, then the MainActivity will handle the onKeyDown
		// event.
		// / (see how onKeyDown is defined in MainActivity and also how
		// onKeyDown is defined in GameScene)
		// and since we only have one Activity, this means that the app will
		// shut
		// down when the user presses the back button.
		// which is exactly what we want; if the back button is pressed during
		// the loading
		// screen, then obviously the user didn't want to use the app in the
		// first place.
		return null;
	}
}

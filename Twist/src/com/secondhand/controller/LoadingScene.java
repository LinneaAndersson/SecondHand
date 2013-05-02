package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import android.content.Context;

import com.secondhand.model.resource.LocalizationStrings;
import com.secondhand.view.resource.Fonts;
import com.secondhand.view.scene.GameScene;
import com.secondhand.view.scene.IGameScene;

public class LoadingScene extends GameScene {
	private AllScenes mSceneEnum = AllScenes.MAIN_MENU_SCENE;
	private String mString = "loading";

	public LoadingScene(final Engine engine, final Context context) {
		super(engine, context);
		Fonts.getInstance().load();

	}

	public void setText(String string) {
		mString = string;
	}

	public void setSceneEnum(AllScenes scene) {
		mSceneEnum = scene;
	}

	@Override
	public void loadScene() {

		//removing the old text from the screen.
		this.detachChildren();
		// add loading text
		this.attachChild(new LoadingText(LocalizationStrings.getInstance()
				.getLocalizedString(mString), this.smoothCamera));

		// in the loading scene we will load all the resources of all the
		// scenes.

		// this is simply done by using a background loading thread:
		// (this allows us to use an animated loading screen)

		final IAsyncCallback callback = new IAsyncCallback() {

			// I want to make this prettier. Now we have an if-statement, which
			// makes it harder to add a new class that needs
			// LoadingScene.
			@Override
			public void work() {
				if (mSceneEnum == AllScenes.MAIN_MENU_SCENE) {
					SceneManager.getInstance().loadAllResources();
				} else if (mSceneEnum == AllScenes.GAME_PLAY_SCENE) {
					SceneManager.getInstance().getGamePlayScene().loadScene();
				}

			}

			@Override
			public void onWorkComplete() {

				// go to next scene when finshed loading
				SceneManager.getInstance().setCurrentSceneEnum(mSceneEnum);

			}
		};

		new AsyncTaskGameLoader().execute(callback);
	}

	@Override
	public AllScenes getParentScene() {
		return null;
	}

}

package com.secondhand.controller;

import org.anddev.andengine.entity.Entity;

import com.secondhand.view.scene.AllScenes;
import com.secondhand.view.scene.LoadingScene;
import com.secondhand.view.scene.LoadingSceneCallback;

public class LoadingSceneController extends Entity {
	
	private final SceneController sceneController;
	
	LoadingSceneController(final LoadingScene loadingScene, 
			final SceneController sceneController) {
		
		this.sceneController = sceneController;
		
		// attach to scene. 
		loadingScene.attachChild(this);
		
		// get view loading text view and attach it to it's controller. 
		loadingScene.attachChild(new LoadingTextController(loadingScene.getLoadingTextView()));
		
		loadingScene.load(new LoadingDoneCallBack());
	}
	
	private class LoadingDoneCallBack implements LoadingSceneCallback {

		@Override
		public void onLoadComplete() {
			sceneController.setGameLoaded(true);
			sceneController.switchScene(AllScenes.GAME_PLAY_SCENE);
		}
		
	}
}

package com.secondhand.controller;

import org.anddev.andengine.entity.Entity;

import com.secondhand.debug.MyDebug;
import com.secondhand.view.scene.AllScenes;
import com.secondhand.view.scene.LoadingScene;
import com.secondhand.view.scene.LoadingSceneCallback;

public class LoadingSceneController extends Entity {
	
	private final SceneController sceneController;
	
	LoadingSceneController(final LoadingScene loadingScene, 
			final SceneController sceneController) {
		super();
		
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
			MyDebug.d("now we should go to gameplay scene");
			sceneController.setGameLoaded(true);
			sceneController.switchScene(AllScenes.MAIN_MENU_SCENE);
		}
		
	}
}

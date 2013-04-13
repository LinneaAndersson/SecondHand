package com.secondhand.controller;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.text.Text;

import com.secondhand.debug.MyDebug;
import com.secondhand.scene.GameScene;
import com.secondhand.twirl.GlobalResources;

public class LoadingScene extends GameScene {

	// specified in milliseconds.
	public static final int MINIMUM_LOADNG_TIME = 1000;

	public LoadingScene(final Camera camera) {
		super(camera);
	}

	@Override
	public void loadResources() {	
		GlobalResources.getInstance().load();			
	}

	private void placeOutLoadingText() {
		Text loadingText = new Text(0, 0, GlobalResources.getInstance().menuFont, "Loading...");
		
		// center the text both horizontally and vertically. 
		float x = this.camera.getWidth() / 2.0f - loadingText.getWidth() / 2.0f;
		float y = this.camera.getHeight() / 2.0f - loadingText.getHeight() / 2.0f;
		loadingText.setPosition(x, y);
		
		this.attachChild(loadingText);
		
	}

	@Override
	public void loadScene() {

		placeOutLoadingText();
		
		// in the loading scene we will load all the resources of all the scenes.
	
		// this is simply done by using a background loading thread:
		// (this allows us to use an animated loading screen)
		
		IAsyncCallback callback = new IAsyncCallback() {

			@Override
			public void work() {

				try{
					// force the loading thread to sleep the minimum loading time before we begin the actual loading. 
					Thread.sleep(MINIMUM_LOADNG_TIME);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}

				// load all resources of all scenes.
				SceneManager.getInstance().loadAllResources();
			}

			@Override
			public void onWorkComplete() {
				MyDebug.d("loaded all resources!");
				
				// go to main menu once the loading is done.
				SceneManager.getInstance().setCurrentSceneEnum(SceneManager.AllScenes.MAIN_MENU_SCENE);
				
			}
		};

		new AsyncTaskGameLoader().execute(callback);
	}	
}

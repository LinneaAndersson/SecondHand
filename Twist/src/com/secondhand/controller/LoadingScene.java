package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import android.content.Context;

import com.secondhand.controller.SceneManager.AllScenes;
import com.secondhand.debug.MyDebug;
import com.secondhand.scene.GameScene;
import com.secondhand.twirl.GlobalResources;

public class LoadingScene extends GameScene {

	// specified in milliseconds.
	// the minimum time the loading screen will be shown.
	public static final int MINIMUM_LOADNG_TIME = 0;

	public LoadingScene(final Engine engine, Context context) {
		super(engine, context);
	}

	@Override
	public void loadResources() {	
		GlobalResources.getInstance().load();			
	}

	@Override
	public void loadScene() {

		// add loading text
		this.attachChild(new LoadingText(this.smoothCamera));
		
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

	@Override
	public AllScenes getParentScene() {
		// If null is returned, then the MainActivity will handle the onKeyDown event.
		/// (see how onKeyDown is defined in MainActivity and also how onKeyDown is defined in GameScene)
		// and since we only have one Activity, this means that the app will shut
		// down when the user presses the back button.
		// which is exactly what we want; if the back button is pressed during the loading 
		// screen, then obviously the user didn't want to use the app in the first place.
		return null;
	}	
}

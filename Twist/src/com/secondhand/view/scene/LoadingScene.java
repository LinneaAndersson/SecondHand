package com.secondhand.view.scene;

import org.anddev.andengine.engine.Engine;

import android.content.Context;

import com.secondhand.view.entity.LoadingTextView;
import com.secondhand.view.resource.Fonts;

public class LoadingScene extends GameScene {

	public LoadingScene(final Engine engine, final Context context) {
		super(engine, context);
		Fonts.getInstance().load();
	}
	
	private LoadingTextView loadingTextView;
	
	public LoadingTextView getLoadingTextView() {
		return this.loadingTextView;
	}

	@Override
	public void loadScene() {
		super.loadScene();
		
		 this.loadingTextView = new LoadingTextView(this.smoothCamera);
		// add loading text
		this.attachChild(this.loadingTextView);
	}

	public void load(final LoadingSceneCallback callback) {

		new AsyncTaskGameLoader().execute(new IAsyncCallback() {

			public void work() {

				callback.doWork();
				
			}

			@Override
			public void onWorkComplete() {
				callback.onLoadComplete();
			}
		});
	}

	@Override
	public AllScenes getParentScene() {
		return null;
	}

}

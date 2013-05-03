package com.secondhand.view.scene;

public interface LoadingSceneCallback {

	
	// do the loading scene's work
	void doWork();
	
	// called when loading is complete. 
	void onLoadComplete();
	
}

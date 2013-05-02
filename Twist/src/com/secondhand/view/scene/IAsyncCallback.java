package com.secondhand.view.scene;

/**
 * Used in conjunction with AsyncTaskGameLoader to create the loading scene.
 */
public interface IAsyncCallback {
	void work();

	void onWorkComplete();
}

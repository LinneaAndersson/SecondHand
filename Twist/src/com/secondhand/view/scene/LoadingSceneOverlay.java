package com.secondhand.view.scene;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.primitive.Rectangle;

import com.secondhand.view.andengine.entity.LoadingTextView;

// a transparent overlay that can be used as a loading scene. 
public class LoadingSceneOverlay extends Entity {

	public LoadingSceneOverlay(final Camera camera) {
		super();
		this.loadingTextView = new LoadingTextView(camera);

		// add loading text
		this.attachChild(this.loadingTextView);

		// transparent rectangle overlay
		final Rectangle overlay = new Rectangle(camera.getMinX(),
				camera.getMinY(), camera.getMaxX(), camera.getMaxY());
		overlay.setColor(0, 0, 0, 0.5f);
		this.attachChild(overlay);
	}

	private final LoadingTextView loadingTextView;

	public LoadingTextView getLoadingTextView() {
		return this.loadingTextView;
	}

}

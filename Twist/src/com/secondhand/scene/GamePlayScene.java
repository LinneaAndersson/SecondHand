package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.background.ColorBackground;

import android.content.Context;

public class GamePlayScene extends GameScene {
	
	public GamePlayScene(Engine engine, Context context) {
		super(engine, context);
	}

	@Override
	public void loadResources() {
		// load the resources of this scene
	}

	@Override
	public void loadScene() {
		// now load the scene(attach all the entities)
		setBackground(new ColorBackground(50f, 0f, 0f));
	}
}

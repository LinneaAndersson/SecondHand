package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;

public class GamePlayScene extends GameScene {

	// this way of doing things will not work with the SceneManager.
	// the data(entities) to be used will be loaded from a file in loadResources().
	/*
	public GamePlayScene (List<IEntity> entities, ColorBackground background) {
		for (IEntity entity : entities)
			attachChild(entity);
		setBackground(background);
	}*/
	
	public GamePlayScene(Engine engine) {
		super(engine);
	}

	@Override
	public void loadResources() {
		// load the resources of this scene
	}

	@Override
	public void loadScene() {
		// now load the scene(attach all the entities)
	}
}

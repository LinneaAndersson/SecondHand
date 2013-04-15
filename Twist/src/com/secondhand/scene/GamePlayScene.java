package com.secondhand.scene;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;

import com.secondhand.loader.TextureRegionLoader;

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
		setBackground(new ColorBackground(1f, 1f, 1f));
	}
}

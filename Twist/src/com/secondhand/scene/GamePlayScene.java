package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.controller.ITouchController;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.loader.TextureRegionLoader;
import com.secondhand.model.Player;

import android.content.Context;

public class GamePlayScene extends GameScene {

	// this way of doing things will not work with the SceneManager.
	// the data(entities) to be used will be loaded from a file in loadResources().
	/*
	public GamePlayScene (List<IEntity> entities, ColorBackground background) {
		for (IEntity entity : entities)
			attachChild(entity);
		setBackground(background);
	}*/
	
	Sprite player_sprite;
	
	IOnSceneTouchListener player_controller;
	
	public GamePlayScene(Engine engine, Context context) {
		super(engine, context);
	}

	@Override
	public void loadResources() {
		// load the resources of this scene
		player_sprite = new Sprite(0, 0, TextureRegionLoader.getInstance().loadTextureRegion("gfx/player.png", 512, 512)); // the path variable in TextureRegionLoader didnt provide "gfx/" correctly for me
		player_controller = new IOnSceneTouchListener() {
			
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
				float centerX = pSceneTouchEvent.getX() - player_sprite.getWidth()/2;
				float centerY = pSceneTouchEvent.getY() - player_sprite.getHeight()/2;
				player_sprite.setPosition(centerX, centerY);
				return true;
			}
		};
		this.setOnSceneTouchListener(player_controller);
	}

	@Override
	public void loadScene() {
		// now load the scene(attach all the entities)
		setBackground(new ColorBackground(1f, 1f, 1f));
		attachChild(player_sprite);
	}

}

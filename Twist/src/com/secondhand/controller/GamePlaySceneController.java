package com.secondhand.controller;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.loader.TextureRegionLoader;
import com.secondhand.model.Universe;
import com.secondhand.scene.GamePlayScene;

public class GamePlaySceneController {

	// View
	private GamePlayScene scene;
	private GameSceneTouchListener sceneListener;
	
	// Model
	private Universe universe = Universe.getInstance();
	
	// Player sprite
	private Sprite player;
	
	public GamePlaySceneController(Scene gamePlayScene) {
		scene = (GamePlayScene) gamePlayScene;
		scene.registerUpdateHandler(universe.getLevel().getPhysicsWorld());
		sceneListener = new GameSceneTouchListener();
		scene.setOnSceneTouchListener(sceneListener);

		player = new Sprite(0, 0, TextureRegionLoader.getInstance().loadTextureRegion("gfx/player.png", 512, 512));
		
		scene.attachChild(player);
		universe.setSprite(player);
	}
	
	private class GameSceneTouchListener implements IOnSceneTouchListener{
		@Override
		public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
			float x = pSceneTouchEvent.getX();
			float y = pSceneTouchEvent.getY();
			universe.update(new Vector2(x, y));
			return true;
		}
	}
}

package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Universe;
import com.secondhand.scene.GamePlayScene;

public final class GamePlaySceneController {

	private final Universe universe;
	
	public GamePlaySceneController(final GamePlayScene scene) {
		universe = scene.getUniverse();
		scene.setOnSceneTouchListener(new GameSceneTouchListener());

	}
	
	private class GameSceneTouchListener implements IOnSceneTouchListener {
		@Override
		public boolean onSceneTouchEvent(final Scene pScene,
				final TouchEvent pSceneTouchEvent) {
			if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
				final float posX = pSceneTouchEvent.getX();
				final float posY = pSceneTouchEvent.getY();
				universe.update(new Vector2(posX, posY));
				return true;
			}
			return false;
		}
	}
}

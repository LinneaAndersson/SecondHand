package com.secondhand.controller;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.input.touch.TouchEvent;

import com.secondhand.model.Universe;
import com.secondhand.opengl.Circle;
import com.secondhand.scene.GamePlayScene;

public class GamePlaySceneController {

	// View
	private GamePlayScene scene;
	
	// Model
	private Universe universe;
	
	// Player sprite
	private Circle player;
	
	public GamePlaySceneController(Scene gamePlayScene) {
		scene = (GamePlayScene) gamePlayScene;
		universe = Universe.getInstance();
		player = new Circle(0, 0, universe.getLevel().getPlayer().getRadius());
	}
	
	private class GameSceneTouchListener implements IOnSceneTouchListener{
		@Override
		public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {	
			return false;
		}
	}
}

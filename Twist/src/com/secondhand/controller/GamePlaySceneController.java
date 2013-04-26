package com.secondhand.controller;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Entity;
import com.secondhand.model.Level;
import com.secondhand.model.Universe;
import com.secondhand.scene.GamePlayScene;

public final class GamePlaySceneController {

	//private GamePlaySceneController instance;
	
	public GamePlaySceneController(final GamePlayScene scene) {

		final Universe universe = Universe.getInstance();
		
		final Level currentLevel = universe.getLevel();
		
		scene.registerUpdateHandler(currentLevel.getPhysicsWorld());
		scene.setOnSceneTouchListener(new GameSceneTouchListener());
		currentLevel.getPlayer().addListener(scene);
		
		final List<IShape> shapes = new ArrayList<IShape>();

		scene.setPlayer(currentLevel.getPlayer().getShape());

		for (final Entity entity : currentLevel.getEntityList()) {
			shapes.add(entity.getShape());
		}
		scene.setShapes(shapes);
		
		currentLevel.getPhysicsWorld().setContactListener(new CollisionContactListener());
		currentLevel.setView(scene);
	}
	
	private class GameSceneTouchListener implements IOnSceneTouchListener {
		@Override
		public boolean onSceneTouchEvent(final Scene pScene,
				final TouchEvent pSceneTouchEvent) {
			if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
				final float posX = pSceneTouchEvent.getX();
				final float posY = pSceneTouchEvent.getY();
				Universe.getInstance().update(new Vector2(posX, posY));
				return true;
			}
			return false;
		}
	}
}

package com.secondhand.controller;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.Entity;
import com.secondhand.model.Universe;
import com.secondhand.scene.GamePlayScene;

public class GamePlaySceneController {

	// View
	private GamePlayScene scene;
	private GameSceneTouchListener sceneListener;

	// Model
	private Universe universe = Universe.getInstance();

	public GamePlaySceneController(Scene gamePlayScene) {
		scene = (GamePlayScene) gamePlayScene;
		scene.registerUpdateHandler(universe.getLevel().getPhysicsWorld());
		sceneListener = new GameSceneTouchListener();
		scene.setOnSceneTouchListener(sceneListener);
		
		List<IShape> shapes = new ArrayList<IShape>();
		
		scene.setPlayer(universe.getLevel().getPlayer().getShape());
		scene.setShapes(shapes);
		
		for(Entity entity: universe.getLevel().getEntityList()) {
			shapes.add(entity.getShape());
		}		
	}

	private class GameSceneTouchListener implements IOnSceneTouchListener {
		@Override
		public boolean onSceneTouchEvent(Scene pScene,
				TouchEvent pSceneTouchEvent) {
			MyDebug.i("TouchEvent");
			if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN ||
					pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE) {
				float x = pSceneTouchEvent.getX();
				float y = pSceneTouchEvent.getY();
				universe.update(new Vector2(x, y));
				return true;
			}
			return false;
		}
	}
}

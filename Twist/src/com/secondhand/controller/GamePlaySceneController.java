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

	// Player sprite
	// TODO: why do we need this here? Should not instead the GamePlayScene handle both
	// setting the player and moving it?
	private IShape player;

	public GamePlaySceneController(Scene gamePlayScene) {
		scene = (GamePlayScene) gamePlayScene;
		scene.registerUpdateHandler(universe.getLevel().getPhysicsWorld());
		sceneListener = new GameSceneTouchListener();
		scene.setOnSceneTouchListener(sceneListener);
		
		player = universe.getLevel().getPlayer().getShape();
		
		player.detachSelf();
		scene.setPlayer(player);
		
		List<IShape> shapes = new ArrayList<IShape>();
		for(Entity entity: universe.getLevel().getEntityList()) {
			shapes.add(entity.getShape());
		}
		scene.setShapes(shapes);
		
		// add the world bounds
		// you can���t attachChild here! it can cause trouble
		/*for(Shape shape: universe.getLevel().getWorldBounds()) {
			shape.detachSelf();	
			scene.attachChild(shape);
		}*/
		
		for(Entity entity: universe.getLevel().getEntityList()) {
			IShape shape = entity.getShape();
		//	shape.detachSelf();	
			scene.attachChild(shape);
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

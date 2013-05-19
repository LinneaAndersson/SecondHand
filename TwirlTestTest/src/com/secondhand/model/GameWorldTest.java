package com.secondhand.model;

import java.util.Random;


import junit.framework.TestCase;

import com.secondhand.model.entity.GameWorld;
import com.secondhand.model.physics.ICollisionResolver;
import com.secondhand.model.physics.IPhysicsWorld;
import com.secondhand.model.physics.Vector2;

public class GameWorldTest extends TestCase {
	
	private class TestPhysicsWorld implements IPhysicsWorld{

		@Override
		public void setWorldBounds(int levelWidth, int levelHeight) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeWorldBounds() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isAreaUnOccupied(float x, float y, float r) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Vector2 getRandomUnOccupiedArea(int worldWidth, int worldHeight,
				float r, Random rng) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setContactListener() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void unsetContactListener() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setCollisionResolver(ICollisionResolver collisionResolver) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	int lives = 3;
	int levelNumber = 1;
	int score = 100;

	public void testConstructor() {

		
		IPhysicsWorld physics = new TestPhysicsWorld();
		
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score);

		assertEquals(levelNumber, gWorld.getLevelNumber());
		assertEquals(score, gWorld.getPlayer().getScore());
		assertEquals(lives, gWorld.getPlayer().getLives());
		assertEquals(physics, gWorld.getPhysics());

	}
	
	public void testUpdateGameWorld(){
		IPhysicsWorld physics = new TestPhysicsWorld();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score);
		
		// PLayer not big enough
		gWorld.updateGameWorld();
		assertEquals(levelNumber, gWorld.getLevelNumber());
		
		
	}
	
	
	
}

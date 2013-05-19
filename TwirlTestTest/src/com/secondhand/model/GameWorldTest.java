package com.secondhand.model;

import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.GameWorld;
import com.secondhand.model.physics.ICollisionResolver;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.physics.IPhysicsWorld;
import com.secondhand.model.physics.Vector2;

public class GameWorldTest extends TestCase {

	private class TestPhysicsWorld implements IPhysicsWorld {

		@Override
		public void setWorldBounds(int levelWidth, int levelHeight) {
		}

		@Override
		public void removeWorldBounds() {
		}

		@Override
		public boolean isAreaUnOccupied(float x, float y, float r) {
			return false;
		}

		@Override
		public Vector2 getRandomUnOccupiedArea(int worldWidth, int worldHeight,
				float r, Random rng) {
			return null;
		}

		@Override
		public void setContactListener() {
		}

		@Override
		public void unsetContactListener() {
		}

		@Override
		public void setCollisionResolver(ICollisionResolver collisionResolver) {
		}

	}

	private class TestPhysicsEntity implements IPhysicsEntity {

		@Override
		public float getCenterX() {
			return 0;
		}

		@Override
		public float getCenterY() {
			return 0;
		}

		@Override
		public void deleteBody() {
		}

		@Override
		public void applyImpulse(Vector2 impulsePosition, float maxSpeed) {
		}

		@Override
		public void applyImpulse(Vector2 impulse, Vector2 impulsePosition) {
		}

		@Override
		public void setLinearDamping(float f) {
		}

		@Override
		public void detachSelf() {
		}

		@Override
		public float computePolygonRadius(List<Vector2> polygon) {
			return 0;
		}

		@Override
		public void setTransform(Vector2 position) {
		}

		@Override
		public void stopMovment() {
		}

		@Override
		public boolean isStraightLine(IPhysicsObject entity,
				IPhysicsObject enemy) {
			return true;
		}

	}

	int lives = 1;
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

	public void testIsGameOver() {
		IPhysicsWorld physics = new TestPhysicsWorld();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score);

		assertEquals(gWorld.getPlayer().lostAllLives(), gWorld.isGameOver());

	}

	public void testUpdateGameWorld() {
		IPhysicsWorld physics = new TestPhysicsWorld();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score);
		for (Entity e : gWorld.getEntityList()) {
			e.setPhysics(new TestPhysicsEntity());
		}

		// PLayer not big enough
		// gWorld.updateGameWorld();
		assertEquals(levelNumber, gWorld.getLevelNumber());

	}

}

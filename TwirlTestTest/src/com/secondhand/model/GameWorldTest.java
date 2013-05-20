package com.secondhand.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.GameWorld;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.ICollisionResolver;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.physics.IPhysicsWorld;
import com.secondhand.model.physics.Vector2;

public class GameWorldTest extends TestCase implements PropertyChangeListener{

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
			return new Vector2(20, 20);
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
		private Vector2 pos = new Vector2();
		@Override
		public float getCenterX() {
			return pos.x;
		}

		@Override
		public float getCenterY() {
			return pos.y;
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
			pos = position;
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

	private int lives = 1;
	private int levelNumber = 1;
	private int score = 100;
	private String name;
	private PropertyChangeSupport support = new PropertyChangeSupport(this);
	// TODO test that enteties are properly removed from entityList.
	// PropertyChangeTest for entityManager
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
		//TODO entityManager part
		IPhysicsWorld physics = new TestPhysicsWorld();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score);
		gWorld.addListener(this);
		for (Entity e : gWorld.getEntityList()) {
			e.setPhysics(new TestPhysicsEntity());
		}
		gWorld.getPlayer().setPhysics(new TestPhysicsEntity());

		// PLayer not big enough
		gWorld.updateGameWorld();
		assertEquals(levelNumber, gWorld.getLevelNumber());

		// Player bigger than maxSize
		gWorld.getPlayer().setRadius(gWorld.getPlayer().getMaxSize());
		gWorld.updateGameWorld();
		assertEquals("NextLevel", name);
		assertEquals(levelNumber+1, gWorld.getLevelNumber());
	}
	
	public void tesPpropertyChange(){
		IPhysicsWorld physics = new TestPhysicsWorld();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score);
		for (Entity e : gWorld.getEntityList()) {
			e.setPhysics(new TestPhysicsEntity());
		}
		Player player = gWorld.getPlayer();
		player.setPhysics(new TestPhysicsEntity());
		support.addPropertyChangeListener(gWorld);
		support.firePropertyChange(Player.RANDOMLY_REPOSITION_PLAYER,true,false);
		
		assertEquals(player.getCenterX(), 20);
		assertEquals(player.getCenterY(), 20);
		
		support.removePropertyChangeListener(gWorld);
		
		
	}
	
	public void testPropertyChangeInManager(){
		
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		name = event.getPropertyName();
	}

}

package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.buffer.BufferObjectManager;

import com.badlogic.gdx.math.Vector2;

import junit.framework.TestCase;

public class EnemyTest extends TestCase{

	private final BufferObjectManager mBufferObjectManager = new BufferObjectManager();
	
	public void setUp() {
		// if we don't do this, an exception is thrown when calling the constructor of Circle.
		BufferObjectManager.setActiveInstance(mBufferObjectManager);
	}
	
	public void testConstructor() {

		final GameWorld gW = new GameWorld();
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		float maxSpeed = 10f;
		
		Enemy enemy = new Enemy(pos, rad, gW, maxSpeed);
		
		assertEquals(rad, enemy.getRadius());
		assertEquals(pos.x, enemy.getX());
		assertEquals(pos.y, enemy.getY());
		assertEquals(maxSpeed, enemy.getMaxSpeed());
	}
	
	
	public void testIsBiggerThan() {
		
		
		final GameWorld gW = new GameWorld();		
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		float maxSpeed = 10f;
		
		Enemy enemy = new Enemy(pos, rad, gW, maxSpeed);
	
		Player other = new Player(pos, rad-1, gW, maxSpeed);
		assertTrue(enemy.canEat(other));
		other = new Player(pos, rad, gW, maxSpeed);
		assertFalse(enemy.canEat(other));
	}
	
}

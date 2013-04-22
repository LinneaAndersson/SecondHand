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

		final PhysicsWorld pw  =new PhysicsWorld(new Vector2(), true);
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Enemy enemy = new Enemy(pos, rad, pw);
		
		assertEquals(rad, enemy.getRadius());
		assertEquals(pos.x, enemy.getX());
		assertEquals(pos.y, enemy.getY());
	}
	
	
	public void testIsBiggerThan() {
		
		
		final PhysicsWorld pw  =new PhysicsWorld(new Vector2(), true);
		
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Enemy enemy = new Enemy(pos, rad, pw);
	
		Player other = new Player(pos, rad-1, pw);
		assertTrue(enemy.canEat(other));
		other = new Player(pos, rad, pw);
		assertFalse(enemy.canEat(other));
	}
	
}

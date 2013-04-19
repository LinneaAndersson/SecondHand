package com.secondhand.model;

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

		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Enemy enemy = new Enemy(pos, rad);
		
		assertEquals(rad, enemy.getRadius());
		assertEquals(pos.x, enemy.getShape().getX());
		assertEquals(pos.y, enemy.getShape().getY());
	}
	
	public void testIncreaseSize() {
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		float inc = 0.3f;
		
		Enemy enemy = new Enemy(pos, rad);
		enemy.increaseSize(inc);
		
		assertEquals(rad + inc, enemy.getRadius());
	}
	
	public void testIsBiggerThan() {
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Enemy enemy = new Enemy(pos, rad);
	
		Player other = new Player(pos, rad-1);
		assertTrue(enemy.canEat(other));
		other = new Player(pos, rad);
		assertFalse(enemy.canEat(other));
	}
	
}

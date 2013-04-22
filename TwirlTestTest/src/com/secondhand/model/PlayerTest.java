package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{

	public void testConstructor() {
		
		final PhysicsWorld pw  =new PhysicsWorld(new Vector2(), true);
		
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Player player = new Player(pos, rad, pw);
		
		assertEquals(rad, player.getRadius());
		assertEquals(pos.x, player.getX());
		assertEquals(pos.y, player.getY());
		assertEquals(true, player.isEdible());
		
	}
	
	public void testIncreaseSize() {
		final PhysicsWorld pw  =new PhysicsWorld(new Vector2(), true);
		
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		float inc = 0.3f;
		
		Player player = new Player(pos, rad, pw);
		player.increaseSize(inc);
		
		assertEquals(rad + inc, player.getRadius());
	}	
	
	public void testIsBiggerThan() {
		final PhysicsWorld pw  =new PhysicsWorld(new Vector2(), true);
		
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Player player = new Player(pos, rad, pw);
	
		Player other = new Player(pos, rad-1, pw);
		assertTrue(player.canEat(other));
		other = new Player(pos, rad, pw);
		assertFalse(player.canEat(other));
	}
	
}

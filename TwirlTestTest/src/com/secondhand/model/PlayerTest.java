package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{

	public void testConstructor() {
		
		final GameWorld gW = new GameWorld();		
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		float maxSpeed = 10f;
		
		Player player = new Player(pos, rad, gW, maxSpeed);
		
		assertEquals(rad, player.getRadius());
		assertEquals(pos.x, player.getX());
		assertEquals(pos.y, player.getY());
		assertEquals(true, player.isEdible());
		
	}
	
	public void testIsBiggerThan() {
		final GameWorld gW = new GameWorld();		
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		float maxSpeed = 10f;
		
		Player player = new Player(pos, rad, gW, maxSpeed);
	
		Player other = new Player(pos, rad-1, gW, maxSpeed);
		assertTrue(player.canEat(other));
		other = new Player(pos, rad, gW, maxSpeed);
		assertFalse(player.canEat(other));
	}
	
}

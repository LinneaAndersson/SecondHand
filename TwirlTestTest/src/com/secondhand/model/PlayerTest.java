package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{

	public void testConstructor() {
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Player player = new Player(pos, rad);
		
		assertEquals(rad, player.getRadius());
		assertEquals(pos.x, player.getX());
		assertEquals(pos.y, player.getY());
		assertEquals(true, player.isEdible());
		
	}
	
	public void testIncreaseSize() {
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		float inc = 0.3f;
		
		Player player = new Player(pos, rad);
		player.increaseSize(inc);
		
		assertEquals(rad + inc, player.getRadius());
	}	
	
	public void testIsBiggerThan() {
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Player player = new Player(pos, rad);
	
		Player other = new Player(pos, rad-1);
		assertTrue(player.canEat(other));
		other = new Player(pos, rad);
		assertFalse(player.canEat(other));
	}
	
}

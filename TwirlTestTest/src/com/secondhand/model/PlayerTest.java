package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{

	public void testConstructor() {
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Player player = new Player(pos, rad);
		
		assertEquals(rad, player.getRadius());
		assertEquals(pos.x, player.getPosition().x);
		assertEquals(pos.y, player.getPosition().y);
	}
	
}

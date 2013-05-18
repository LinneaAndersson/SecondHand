package com.secondhand.model;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{

	public void testConstructor() {
		
		
		Player player = new Player(new Vector2(), 10, 3, // lives
				100, // starting score
				200); // maxsize
		
		assertEquals(200, player.getMaxSize());
		assertEquals(false, player.isMirroredMovement());
		assertEquals(1, player.getSpeedMultiplier(), 0.0001f);
		assertEquals(1, player.getScoreMultiplier(), 0.0001f);
		assertEquals(3, player.getLives());
	}

}

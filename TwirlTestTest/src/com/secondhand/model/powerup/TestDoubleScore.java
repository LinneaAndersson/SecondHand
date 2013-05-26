package com.secondhand.model.powerup;


import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class TestDoubleScore extends TestCase {

	public void testEffet() {
		Player player = new Player(new Vector2(), 10f, 3, 0, 100);
		DoubleScore powerup = new DoubleScore(new Vector2());
		
		assertEquals(10, powerup.getDuration(), 0.001);
		assertEquals(5, DoubleScore.getFrequency());	
		
		assertEquals(1, powerup.getR(), 0.001);	
		assertEquals(1, powerup.getG(), 0.001);	
		assertEquals(0, powerup.getB(), 0.001);	
		
		assertEquals(PowerUpType.DOUBLE_SCORE, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		assertEquals(2, player.getScoreMultiplier(), 0.001);
		
		powerup.deactivateEffect(player, true);
		assertEquals(2, player.getScoreMultiplier(), 0.001);
		
		powerup.deactivateEffect(player, false);
		assertEquals(1, player.getScoreMultiplier(), 0.001);
		
	}
	
}

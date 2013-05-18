package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class ScoreUpTest extends TestCase {

	public void testEffet() {
		Player player = new Player(new Vector2(), 10f, 3, 0, 100);
		ScoreUp powerup = new ScoreUp(new Vector2());
		
		assertEquals(0, powerup.getDuration(), 0.001);
		assertEquals(9, ScoreUp.getFrequency());	
		
		
		assertEquals(PowerUpType.SCORE_UP, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		
		
		assertEquals(10, player.getScore());	
		assertEquals("10+", powerup.getText());	
		
		// now do it with a score multiplier:
		player.setScoreMultiplier(3);
		powerup.activateEffect(player);
		
		
		assertEquals(10 + 30, player.getScore());	
		assertEquals("30+", powerup.getText());	
		
	}
	
}
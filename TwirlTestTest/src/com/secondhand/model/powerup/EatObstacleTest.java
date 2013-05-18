package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class EatObstacleTest extends TestCase {

	public void testEffet() {
		Player player = new Player(new Vector2(), 10f, 3, 0, 100);
		EatObstacle powerup = new EatObstacle(new Vector2());
		
		assertEquals(5, powerup.getDuration(), 0.001);
		assertEquals(3, EatObstacle.getFrequency());	
		
		assertEquals(1, powerup.getR(), 0.001);	
		assertEquals(0, powerup.getG(), 0.001);	
		assertEquals(0, powerup.getB(), 0.001);	
		
		assertEquals(PowerUpType.EAT_OBSTACLE, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		assertTrue(player.canEatInedibles());
		
		powerup.deactivateEffect(player, true);
		assertTrue(player.canEatInedibles());
		
		powerup.deactivateEffect(player, false);
		assertFalse(player.canEatInedibles());
	}
	
}

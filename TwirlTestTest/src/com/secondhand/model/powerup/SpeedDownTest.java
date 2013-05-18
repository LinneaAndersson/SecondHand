package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class SpeedDownTest extends TestCase {

	public void testEffet() {
		Player player = new Player(new Vector2(), 10f, 3, 0, 100);
		SpeedDown powerup = new SpeedDown(new Vector2());
		
		assertEquals(10, powerup.getDuration(), 0.001);
		assertEquals(7, SpeedDown.getFrequency());	
		
		assertEquals(0, powerup.getR(), 0.001);	
		assertEquals(1, powerup.getG(), 0.001);	
		assertEquals(1, powerup.getB(), 0.001);	
		
		assertEquals(PowerUpType.SPEED_DOWN, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		assertEquals(0.5, player.getSpeedMultiplier(), 0.0001);
		
		powerup.activateEffect(player);
		assertEquals(0.25, player.getSpeedMultiplier(), 0.0001);
		
		powerup.deactivateEffect(player, true);
		assertEquals(0.5, player.getSpeedMultiplier(), 0.0001);
		
		powerup.deactivateEffect(player, true);
		assertEquals(1, player.getSpeedMultiplier(), 0.0001);
	}
	
}

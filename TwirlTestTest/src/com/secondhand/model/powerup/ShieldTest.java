package com.secondhand.model.powerup;


import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class ShieldTest extends TestCase {

	public void testEffet() {
		Player player = new Player(new Vector2(), 10f, 3, 0, 100);
		Shield powerup = new Shield(new Vector2());
		
		assertEquals(4, powerup.getDuration(), 0.001);
		assertEquals(4, Shield.getFrequency());	
		
		assertEquals(0, powerup.getR(), 0.001);	
		assertEquals(1, powerup.getG(), 0.001);	
		assertEquals(0, powerup.getB(), 0.001);	
		
		assertEquals(PowerUpType.SHIELD, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		assertFalse(player.isEdible());
		
		powerup.deactivateEffect(player, true);
		assertFalse(player.isEdible());
		
		powerup.deactivateEffect(player, false);
		assertTrue(player.isEdible());
	}
	
}

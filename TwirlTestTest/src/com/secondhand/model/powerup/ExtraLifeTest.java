package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class ExtraLifeTest extends TestCase {

	public void testEffet() {
		Player player = new Player(new Vector2(), 10f, 3, 0, 100);
		ExtraLife powerup = new ExtraLife(new Vector2());
		
		assertEquals(0, powerup.getDuration(), 0.001);
		assertEquals(1, ExtraLife.getFrequency());	
		assertEquals("1UP", powerup.getText());	
		
		assertEquals(PowerUpType.EXTRA_LIFE, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		
		assertEquals(4, player.getLives());
		
	}
	
}
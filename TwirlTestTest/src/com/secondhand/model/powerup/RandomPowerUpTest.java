package com.secondhand.model.powerup;

import java.util.Random;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class RandomPowerUpTest extends TestCase {

	public void testInstantPowerUp() {
		
		// we'll do it for the extra life powerup
		
		class MockRandom extends Random
		{	
			private static final long serialVersionUID = 1L;

			public int nextInt(int n)
			{
				// always extra life power up
				return 1;
			}
		}

		Player player = new Player(new Vector2(), 10f, 3, 0, 100);
		RandomPowerUp powerup = new RandomPowerUp(new Vector2(), new MockRandom());
		
		assertEquals(0, powerup.getDuration(), 0.001);
		assertEquals(6, RandomPowerUp.getFrequency());	
		assertEquals("1UP", powerup.getText());	
		
		assertEquals(PowerUpType.RANDOM_POWER_UP, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		
		assertEquals(4, player.getLives());
	}
	
	public void testTimedPowerUp() {
		
		class MockRandom extends Random
		{	
			private static final long serialVersionUID = 1L;

			public int nextInt(int n)
			{
				// always extra life power up
				return 0;
			}
		}

		
		Player player = new Player(new Vector2(), 10f, 3, 0, 100);
		RandomPowerUp powerup = new RandomPowerUp(new Vector2(), new MockRandom());
		
		assertEquals(5, powerup.getDuration(), 0.001);
		assertEquals(6, RandomPowerUp.getFrequency());	
		
		assertEquals(1, powerup.getR(), 0.001);	
		assertEquals(0, powerup.getG(), 0.001);	
		assertEquals(0, powerup.getB(), 0.001);	
		
		assertEquals(PowerUpType.RANDOM_POWER_UP, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		assertTrue(player.canEatInedibles());
		
		powerup.deactivateEffect(player, true);
		assertTrue(player.canEatInedibles());
		
		powerup.deactivateEffect(player, false);
		assertFalse(player.canEatInedibles());
	}
	
}
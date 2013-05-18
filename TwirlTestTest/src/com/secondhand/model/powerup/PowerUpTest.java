package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class PowerUpTest extends TestCase {
	
	public static PowerUp getNewPowerUp(Vector2 position, PowerUpType powerUpType, float duration) {
		return new PowerUp(position, powerUpType, duration) {
			@Override
			public void activateEffect(Player player) {}
			
			@Override
			public void activatePowerUp(Player player) {}
			
			@Override
			public String getText(){
				return "lorem ipsum";
			}
		};
	}
	
	public void testConstructor() {
		Vector2 position = new Vector2(1,2);
		PowerUpType powerUpType = PowerUpType.EXTRA_LIFE;
		float duration = 5;
		
		PowerUp powerUp = getNewPowerUp(position, powerUpType, duration);
		
		assertEquals(powerUpType, powerUp.getPowerUpType());
		assertEquals(duration, powerUp.getDuration());
		
		assertEquals(PowerUp.WIDTH, powerUp.getWidth());
		assertEquals(PowerUp.WIDTH, powerUp.getHeight());
		assertEquals(0, powerUp.getRadius());
		assertEquals(0, powerUp.getScoreValue());
		assertTrue(powerUp.hasText());
		
	}
	
	public void testAddPowerUp() {
		
	}
}

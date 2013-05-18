package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.entity.PowerUpList;
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
		};
	}
	
	public void testConstructor() {
		Vector2 position = new Vector2();
		PowerUpType powerUpType = PowerUpType.EXTRA_LIFE;
		float duration = 5;
		
		PowerUp powerUp = getNewPowerUp(position, powerUpType, duration);
		
		assertEquals(position, powerUp.getInitialPosition());
		assertEquals(powerUpType, powerUp.getPowerUpType());
		assertEquals(duration, powerUp.getDuration());
	}
	
	public void testRemovePowerUp() {
		
	}
	
	public void testAddPowerUp() {
		
	}
}

package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class PowerUpTest extends TestCase {
	
	public PowerUp getNewPowerUp(Vector2 position, PowerUpType powerUpType, float duration, Player player) {
		return new PowerUp(position, powerUpType, duration, player) {
			@Override
			public void activateEffect(Player player) {}
		};
	}
	
	public void testConstructor() {
		Vector2 position = new Vector2();
		PowerUpType powerUpType = PowerUpType.EXTRA_LIFE;
		float duration = 5;
		
		PowerUp powerUp = getNewPowerUp(position, powerUpType, duration, new Player(new Vector2(), 10));
		
		assertEquals(position, powerUp.getInitialPosition());
		assertEquals(powerUpType, powerUp.getPowerUpType());
		assertEquals(duration, powerUp.getDuration());
	}
	
	public void testHasAnother() {
		PowerUpList powerUpList = PowerUpList.getInstance();
		
		// Same PowerUpType but different positions,radius etc..
		powerUpList.add(getNewPowerUp(new Vector2(1,2), PowerUpType.DOUBLE_SCORE, 11, new Player(new Vector2(1,2), 11)));
		powerUpList.add(getNewPowerUp(new Vector2(1,1), PowerUpType.DOUBLE_SCORE, 10, new Player(new Vector2(1,1), 10)));
		
		assertTrue(powerUpList.get(0).hasAnother());
	}
	
	public void testRemovePowerUp() {
		
	}
	
	public void testAddPowerUp() {
		
	}
}

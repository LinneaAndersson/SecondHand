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
		
		assertEquals(PowerUp.WIDTH, powerUp.getWidth(), 0.001f);
		assertEquals(PowerUp.WIDTH, powerUp.getHeight(), 0.001f);
		assertEquals(0, powerUp.getRadius(), 0.001f);
		assertEquals(0, powerUp.getScoreValue());
		assertTrue(powerUp.hasText());
	
		assertEquals(Player.DEFAULT_COLOR_VALUE, powerUp.getR(), 0.001);
		assertEquals(Player.DEFAULT_COLOR_VALUE, powerUp.getG(), 0.001);
		assertEquals(Player.DEFAULT_COLOR_VALUE, powerUp.getB(), 0001f);
		
		
		
	}
	
	public void testAddPowerUp() {
		
	}
	
	public void testResetPlayerColor() {
		final float[] RGB = new float[3];	

		Player player = new Player(new Vector2(),3.0f, 3, 1, 0);

		
		Vector2 position = new Vector2(1,2);
		PowerUpType powerUpType = PowerUpType.EXTRA_LIFE;
		float duration = 5;
		
		
		RGB[0] = 0.1f;
		RGB[1] = 0.2f;
		RGB[2] = 0.3f;
		player.setRGB(RGB);
	
		PowerUp powerUp = getNewPowerUp(position, powerUpType, duration);
		powerUp.resetPlayerColor(player);	
		
		assertEquals(Player.DEFAULT_COLOR_VALUE, powerUp.getR(), 0.001);
		assertEquals(Player.DEFAULT_COLOR_VALUE, powerUp.getG(), 0.001);
		assertEquals(Player.DEFAULT_COLOR_VALUE, powerUp.getB(), 0.001);
	}
}

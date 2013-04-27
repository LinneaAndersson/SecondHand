package com.secondhand.model.powerup;

import junit.framework.TestCase;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;

public class PowerUpTest extends TestCase {
	
	public void testConstructor() {
		final GameWorld gW = new GameWorld();
		final Vector2 position = new Vector2(2,2);
		float dur = 5;
		
		PowerUp pu1 = new PowerUp(position, gW, dur) {
			@Override
			public void activateEffect(Player player) {
				
			}
		};
		
		assertEquals(position.x, pu1.getX());
		assertEquals(position.y, pu1.getY());
		assertEquals(pu1.getDuration(), dur);
		assertEquals(true, pu1.isEdible());
	}
	
	public void testDeactivateEffect() {
		Vector2 pos = new Vector2();
		final GameWorld gW = new GameWorld();
		float rad = 10;
		float maxSpeed = 20;
		Player player = new Player(pos, rad, gW, maxSpeed);
		float duration = 5;
		PowerUp powerUp = new PowerUp(pos, gW, duration) {
			@Override
			public void activateEffect(Player player) {
				player.getCircle().setColor(0, 0, 0); // Set color to black
			}
		};
		
		powerUp.activateEffect(player);
		assertTrue(player.getCircle().getRed() == 0);
		assertTrue(player.getCircle().getGreen() == 0);
		assertTrue(player.getCircle().getBlue() == 0);
		powerUp.deactivateEffect(player); // Color should now be white
		assertTrue(player.getCircle().getRed() == 1);
		assertTrue(player.getCircle().getGreen() == 1);
		assertTrue(player.getCircle().getBlue() == 1);
	}
}

package com.secondhand.model.powerup;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Player;
import com.secondhand.model.powerup.PowerUp;

import junit.framework.TestCase;

public class PowerUpTest extends TestCase {
	
	public void testConstructor() {
		final PhysicsWorld pw = new PhysicsWorld(new Vector2(), true);
		final Vector2 position = new Vector2(2,2);
		
		PowerUp pu1 = new PowerUp(position, pw, 5) {
			@Override
			public void activateEffect(Player player) {
				
			}
		};
		
		assertEquals(position.x, pu1.getX());
		assertEquals(position.y, pu1.getY());
		assertEquals(true, pu1.isEdible());
	}
	
	public void testDeactivateEffect() {
		Vector2 pos = new Vector2();
		float rad = 10;
		PhysicsWorld pw = new PhysicsWorld(new Vector2(), true);
		float maxSpeed = 20;
		Player player = new Player(pos, rad, pw, maxSpeed);
		float duration = 5;
		PowerUp powerUp = new PowerUp(pos, pw, duration) {
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

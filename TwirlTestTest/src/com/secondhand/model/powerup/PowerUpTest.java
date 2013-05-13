package com.secondhand.model.powerup;

import junit.framework.TestCase;

import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.controller.PlayerController;
import com.secondhand.model.entity.GameWorld;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;
import com.secondhand.view.physics.MyPhysicsWorld;

public class PowerUpTest extends TestCase {
	/*
	public void testGetTimer() {
		float duration = 10;
		GameWorld level = new GameWorld(new MyPhysicsWorld(new PhysicsWorld(new com.badlogic.gdx.math.Vector2(),false)));
		Player player = new Player(new Vector2(), 10, level);
		PowerUp powerUp = new PowerUp(new Vector2(), PowerUpType.DOUBLE_SCORE, duration, null) {
			@Override
			public void activateEffect(Player player) {}
		};
		TimerHandler timer = PlayerController.createTimer(player,powerUp);
		
		assertEquals(powerUp.getDuration(), timer.getTimerSeconds());
		
		player.addPowerUp(powerUp);
		timer.setAutoReset(false);
		timer.onUpdate(duration);
		assertTrue(!player.getPowerUps().contains(powerUp));
	}
	
	public void testConstructor() {
		final GameWorld gW = new GameWorld(new MyPhysicsWorld(new PhysicsWorld(new com.badlogic.gdx.math.Vector2(),false)));
		final Vector2 position = new Vector2(2,2);
		float dur = 5;
		
		PowerUp pu1 = new PowerUp(position, PowerUpType.DOUBLE_SCORE, dur, null) {
			@Override
			public void activateEffect(Player player) {
				
			}
		};
		
		assertEquals(position.x, pu1.getCenterX() - pu1.getRadius());
		assertEquals(position.y, pu1.getCenterY() - pu1.getRadius());
		assertEquals(pu1.getDuration(), dur);
		assertEquals(true, pu1.isEdible());
	}
	/*
	public void testDeactivateEffect() {
		Vector2 pos = new Vector2();
		final GameWorld gW = new GameWorld(new MyPhysicsWorld(new PhysicsWorld(new com.badlogic.gdx.math.Vector2(),false)));
		float rad = 10;
		Player player = new Player(pos, rad, gW);
		float duration = 5;
		PowerUp powerUp = new PowerUp(pos, PowerUpType.DOUBLE_SCORE, gW, duration) {
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
	*/
}

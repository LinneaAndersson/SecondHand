package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;

import junit.framework.TestCase;

public class ExtraLifeTest extends TestCase {

	public void testActivateEffect() {
		GameWorld world = new GameWorld();
		int lives = 1;
		Player player = new Player(new Vector2(), 10, world, lives, 0);
		ExtraLife powerUp = new ExtraLife(new Vector2(), world);
		
		assertEquals(lives, player.getLives());
		powerUp.activateEffect(player);
		assertEquals(lives+1, player.getLives());
	}
	
}

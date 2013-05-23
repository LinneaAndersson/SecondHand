package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;

import junit.framework.TestCase;

public class BlackColorTest extends TestCase {

	public void testDeactivateEffect() {
		final Player player = new Player(new Vector2(), 0, 0, 0, 0);
		final BlackColor blackColor = new BlackColor(new Vector2());
		
		final float[] blackColorRGB =
				  { blackColor.getR(), 
					blackColor.getG(), 
					blackColor.getB() };
		player.setRGB(blackColorRGB);
		
		assertEquals(player.getRGB(), blackColorRGB);
		
		blackColor.deactivateEffect(player, false);
		
		final float[] playerRGB = player.getRGB();
		assertEquals(playerRGB[0], Player.DEFAULT_COLOR_VALUE);
		assertEquals(playerRGB[1], Player.DEFAULT_COLOR_VALUE);
		assertEquals(playerRGB[2], Player.DEFAULT_COLOR_VALUE);
	}
}

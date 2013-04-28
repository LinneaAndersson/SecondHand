package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;

import junit.framework.TestCase;

public class ScoreUpTest extends TestCase {

	public void testActivateEffect() {
		Vector2 pos = new Vector2();
		float rad = 5;
		GameWorld level = new GameWorld();
		float maxSpeed = 20;
		Player player = new Player(pos, rad, level, maxSpeed);
		
		ScoreUp powerUp = new ScoreUp(pos, level);
		
		int playerStartScore = player.getScore();
		powerUp.activateEffect(player);
		assertEquals(playerStartScore+powerUp.getScoreBonus(), player.getScore());
	}
	
}

package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;

import junit.framework.TestCase;

public class SpeedUpTest extends TestCase {

	public void testActivateEffect() {
		Vector2 position = new Vector2();
		float radius = 10;
		GameWorld level = new GameWorld();
		float maxSpeed = 10;
		Player player = new Player(position, radius, level, maxSpeed);
	}
	
}

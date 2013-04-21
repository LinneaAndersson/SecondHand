package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.PowerUp.Effect;

import junit.framework.TestCase;

public class PowerUpTest extends TestCase {
	
	public void testConstructor() {
		PowerUp pu1 = new PowerUp(new Vector2(10, 11),Effect.SCORE_UP) {};
		PowerUp pu2 = new PowerUp(new Vector2(),Effect.SPEED_UP) {};
		
		assertEquals(pu1.getEffect(), Effect.SCORE_UP);
		assertEquals(pu2.getEffect(), Effect.SPEED_UP);
		
		assertEquals(10f, pu1.getX());
		assertEquals(11f, pu1.getY());
		assertEquals(true, pu1.isEdible());
	}	
}

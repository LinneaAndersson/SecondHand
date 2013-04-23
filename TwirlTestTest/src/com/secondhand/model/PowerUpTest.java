package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.model.powerup.PowerUp.Effect;

import junit.framework.TestCase;

public class PowerUpTest extends TestCase {
	
	public void testConstructor() {
		final PhysicsWorld pw  =new PhysicsWorld(new Vector2(), true);
		
		
		PowerUp pu1 = new PowerUp(new Vector2(10, 11),Effect.SCORE_UP, pw) {};
		PowerUp pu2 = new PowerUp(new Vector2(),Effect.SPEED_UP, pw) {};
		
		assertEquals(pu1.getEffect(), Effect.SCORE_UP);
		assertEquals(pu2.getEffect(), Effect.SPEED_UP);
		
		assertEquals(10f, pu1.getX());
		assertEquals(11f, pu1.getY());
		assertEquals(true, pu1.isEdible());
	}	
}

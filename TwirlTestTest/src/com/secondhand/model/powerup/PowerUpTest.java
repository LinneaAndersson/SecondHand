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
}

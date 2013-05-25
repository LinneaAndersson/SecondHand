package com.secondhand.model.powerup;

import java.util.Random;

import junit.framework.TestCase;

import com.secondhand.model.physics.Vector2;

public class PowerUpFactoryTest extends TestCase {

	public void testPowerUpFactory() {

		// we'll do it for the extra life powerup

		class MockRandom extends Random {
			int i = 0;
			private static final long serialVersionUID = 1L;

			
			public int nextInt(int n) {
				// always double score
				return i+= 2;
			}
		}

		PowerUpFactory factory = new PowerUpFactory();
		factory.setRandom(new MockRandom());
		
		Vector2 pos;
		PowerUp powerup;
		for (int i = 0; i < 26; i++) {
			pos = new Vector2(i, i);
			powerup = factory.getRandomPowerUp(pos);
			if(powerup != null){
				assertTrue(pos.almostEquals(powerup.getInitialPosition()));
				System.out.println(powerup);
				//assertEquals(tmp[i], powerup.getPowerUpType());					
			}
			
			
		}
	}
}

package com.secondhand.model.powerup;

import java.util.Random;

import junit.framework.TestCase;

import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class PowerUpFactoryTest extends TestCase  {

	// we won't be testing that the random distribution is correct, that is really more trouble than it's worth,
	// but we will be testing that the generated powerup is positioned correctly. 
	public void testPowerUpFactory() {

		// we'll do it for the extra life powerup

		class MockRandom extends Random
		{	
			private static final long serialVersionUID = 1L;

			public int nextInt(int n)
			{
				// always double score
				return 0;
			}
		}

		PowerUpFactory factory = new PowerUpFactory();
		factory.setRandom(new MockRandom());

		Vector2 pos = new Vector2(2,2);
		PowerUp powerup = factory.getRandomPowerUp(pos);
	
		assertTrue(pos.almostEquals(powerup.getInitialPosition()));	
		assertEquals(PowerUpType.DOUBLE_SCORE, powerup.getPowerUpType());	
	}

}

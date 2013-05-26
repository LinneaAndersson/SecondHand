package com.secondhand.model.powerup;

import java.util.Random;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class RandomPowerUpTest extends TestCase {
	class MockRandom extends Random {	
		int i;
		
		private static final long serialVersionUID = 1L;
		
		public MockRandom(int start){
			i = start;
		}
		public int nextInt(int n)
		{
			// always extra life power up
			return i++;
		}
	}
	
public void testConstructor(){
	//Player player = new Player(new Vector2(), 10f, 3, 0, 100);
	Random rng = new MockRandom(0);
	RandomPowerUp powerup;
	
	float[] tmp = new float[]{5,0,0,0,4,10,10,5,10};
	for(int i = 0; i <9; i++){
		powerup = new RandomPowerUp(new Vector2(), rng);
		assertEquals(tmp[i], powerup.getDuration());
	}
	try{
	powerup = new RandomPowerUp(new Vector2(), rng);
	} catch(Exception e){
		assertEquals(NullPointerException.class, e.getClass());
	}
	
	
	}

	public void testInstantPowerUp() {		
		// we'll do it for the extra life powerup

		Player player = new Player(new Vector2(), 10f, 3, 0, 100);
		RandomPowerUp powerup = new RandomPowerUp(new Vector2(), new MockRandom(1));
		
		assertEquals(0, powerup.getDuration(), 0.001);
		assertEquals(6, RandomPowerUp.getFrequency());	
		assertEquals("1UP", powerup.getText());	
		
		assertEquals(PowerUpType.RANDOM_POWER_UP, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		
		assertEquals(4, player.getLives());
	}
	
	public void testTimedPowerUp() {
		
		Player player = new Player(new Vector2(), 10f, 3, 0, 100);

		RandomPowerUp powerup = new RandomPowerUp(new Vector2(), new MockRandom(0));
		
		assertEquals(5, powerup.getDuration(), 0.001);
		assertEquals(6, RandomPowerUp.getFrequency());	
		
		assertEquals(1, powerup.getR(), 0.001);	
		assertEquals(0, powerup.getG(), 0.001);	
		assertEquals(0, powerup.getB(), 0.001);	
		
		assertEquals(PowerUpType.RANDOM_POWER_UP, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		assertTrue(player.canEatInedibles());
		
		powerup.deactivateEffect(player, true);
		assertTrue(player.canEatInedibles());
		
		powerup.deactivateEffect(player, false);
		assertFalse(player.canEatInedibles());
	}
	
	
	
}
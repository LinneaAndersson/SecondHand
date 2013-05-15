package com.secondhand.model;

import com.secondhand.model.entity.Player;
import com.secondhand.model.entity.PowerUpList;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.PowerUpTest;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class PowerUpListTest extends TestCase {
	
	public void testHasAnother() {
		PowerUpList powerUpList = PowerUpList.getInstance();
		
		// Same PowerUpType but different positions,radius etc..
		powerUpList.add(PowerUpTest.getNewPowerUp(new Vector2(1,2), PowerUpType.DOUBLE_SCORE, 11, new Player(new Vector2(1,2), 11)));
		powerUpList.add(PowerUpTest.getNewPowerUp(new Vector2(1,1), PowerUpType.DOUBLE_SCORE, 10, new Player(new Vector2(1,1), 10)));
		
		assertTrue(powerUpList.hasAnother(powerUpList.get(0)));
	}
	
}

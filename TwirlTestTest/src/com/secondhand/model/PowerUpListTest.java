package com.secondhand.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.secondhand.model.entity.Player;
import com.secondhand.model.entity.PowerUpList;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.model.powerup.PowerUpTest;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class PowerUpListTest extends TestCase {
	
	public void testAdd() {
		final PowerUpList powerUpList = PowerUpList.getInstance();
		powerUpList.setPlayer(new Player(new Vector2(), 10));
		
		class AddPowerUpListener implements PropertyChangeListener {
			
			public boolean hasHeardChange = false;
			
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				hasHeardChange = event.getPropertyName().equals(PowerUpList.ADD_POWERUP);
			}
		}
		AddPowerUpListener listener = new AddPowerUpListener();
		powerUpList.addListener(listener);
		
		class PowerUpActivationTester extends PowerUp {
			public boolean isActivated = false;
			
			public PowerUpActivationTester() {
				super(new Vector2(), PowerUpType.DOUBLE_SCORE, 10);
			}
			
			@Override
			public void activateEffect(Player player) {	}
			
			@Override
			public void activatePowerUp(Player player) {
				super.activatePowerUp(player);
				isActivated = true;
			}
		}
		PowerUpActivationTester powerUp = new PowerUpActivationTester();
		powerUpList.add(powerUp);
		
		assertTrue(listener.hasHeardChange);
		assertTrue(powerUp.isActivated);
		assertTrue(powerUpList.contains(powerUp));
	}
	
	public void testHasAnother() {
		PowerUpList powerUpList = PowerUpList.getInstance();
		
		// Same PowerUpType but different positions,radius etc..
		powerUpList.add(PowerUpTest.getNewPowerUp(new Vector2(1,2), PowerUpType.DOUBLE_SCORE, 11, new Player(new Vector2(1,2), 11)));
		powerUpList.add(PowerUpTest.getNewPowerUp(new Vector2(1,1), PowerUpType.DOUBLE_SCORE, 10, new Player(new Vector2(1,1), 10)));
		
		assertTrue(powerUpList.hasAnother(powerUpList.get(0)));
	}
	
}

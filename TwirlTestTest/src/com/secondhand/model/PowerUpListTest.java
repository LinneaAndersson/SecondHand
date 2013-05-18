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
		final PowerUpList powerUpList = new PowerUpList();
		
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
				isActivated = true;
			}
		}
		PowerUpActivationTester powerUp = new PowerUpActivationTester();
		powerUpList.add(powerUp);
		
		assertTrue(listener.hasHeardChange);
		assertTrue(powerUp.isActivated);
		assertTrue(powerUpList.contains(powerUp));
	}
	
	public void testRemove() {
		
		PowerUpList powerUpList = new PowerUpList();
		
		class PowerUpDeactivationTester extends PowerUp {
			
			public boolean isDeactivated = false;
			public final float TEST_VALUE = 0.5f;
			
			public PowerUpDeactivationTester() {
				super(new Vector2(), PowerUpType.DOUBLE_SCORE, 10);
			}
			
			@Override
			public void activateEffect(Player player) {}
			
			@Override
			public void activatePowerUp(Player player) {
				player.setRGB(new float[]{TEST_VALUE,TEST_VALUE,TEST_VALUE});
			}
			
			@Override
			public void deactivateEffect(Player player, boolean hasAnother) {
				isDeactivated = true;
			}
		};
		PowerUpDeactivationTester powerUp = new PowerUpDeactivationTester();
		Player player = new Player(new Vector2(), 10, 3, 0, 0);
		
		powerUpList.setPlayer(player);
		powerUpList.add(powerUp);
		powerUpList.remove(powerUp);
		
		assertTrue(powerUpList.isEmpty());
		assertTrue(powerUp.isDeactivated);
		assertEquals(player.getRGB()[0], powerUp.getR());
		assertEquals(player.getRGB()[1], powerUp.getG());
		assertEquals(player.getRGB()[2], powerUp.getB());
	}
	
	public void testHasAnother() {
		PowerUpList powerUpList = new PowerUpList();
		
		// Same PowerUpType but different positions,radius etc..
		powerUpList.add(PowerUpTest.getNewPowerUp(new Vector2(1,2), PowerUpType.DOUBLE_SCORE, 11, new Player(new Vector2(1,2), 11, 3, 0, 0)));
		powerUpList.add(PowerUpTest.getNewPowerUp(new Vector2(1,1), PowerUpType.DOUBLE_SCORE, 10, new Player(new Vector2(1,1), 10, 3, 0, 0)));
		
		assertTrue(powerUpList.hasAnother(powerUpList.get(0)));
	}
	
}

package com.secondhand.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.TestCase;

import com.secondhand.model.entity.Player;
import com.secondhand.model.entity.PowerUpList;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.model.resource.PowerUpType;

public class PowerUpListTest extends TestCase {
	
	public void testAdd() {
		final PowerUpList powerUpList = new PowerUpList(null);
		
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
				isActivated = (player == null);
			}
		}
		PowerUpActivationTester powerUp = new PowerUpActivationTester();
		powerUpList.add(powerUp);
		
		assertTrue(listener.hasHeardChange);
		assertTrue(powerUp.isActivated);
		assertTrue(powerUpList.contains(powerUp));
	}
	
	Player removeTestPlayer;
	
	public void testRemove() {
		
		removeTestPlayer = new Player(new Vector2(), 10, 3, 0, 0);
		
		final PowerUpList powerUpList = new PowerUpList(removeTestPlayer);
		
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
				isDeactivated = removeTestPlayer == player;
			}
		};
		PowerUpDeactivationTester powerUp = new PowerUpDeactivationTester();
		
		powerUpList.add(powerUp);
		powerUpList.remove(powerUp);
		
		assertTrue(powerUpList.isEmpty());
		assertTrue(powerUp.isDeactivated);
		assertEquals(removeTestPlayer.getRGB()[0], powerUp.getR());
		assertEquals(removeTestPlayer.getRGB()[1], powerUp.getG());
		assertEquals(removeTestPlayer.getRGB()[2], powerUp.getB());
	}
	
	
	public PowerUp getNewPowerUp(Vector2 position, PowerUpType powerUpType, float duration) {
		return new PowerUp(position, powerUpType, duration) {
			@Override
			public void activateEffect(Player player) {}
		};
	}
	
	
	public void testHasAnother() {
		PowerUpList powerUpList = new PowerUpList(new Player(new Vector2(), 10, 3, 0, 0));
		
	
		// Same PowerUpType but different positions,radius etc..
		powerUpList.add(getNewPowerUp(new Vector2(1,2), PowerUpType.DOUBLE_SCORE, 11));
		powerUpList.add(getNewPowerUp(new Vector2(1,1), PowerUpType.DOUBLE_SCORE, 10));
		powerUpList.remove(0);
		
		assertTrue(powerUpList.hasAnother(powerUpList.get(0)));
	}
	
}

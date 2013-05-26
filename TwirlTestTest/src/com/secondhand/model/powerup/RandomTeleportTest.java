package com.secondhand.model.powerup;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

import junit.framework.TestCase;

public class RandomTeleportTest extends TestCase {

	public void testEffet() {
		
		class Listener implements PropertyChangeListener {
			
			public boolean done = false;
			
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				String name = event.getPropertyName();
				if(name.equals(Player.RANDOMLY_REPOSITION_PLAYER)) {
					done = true;
				}
			}
		};
		
		Listener listener = new Listener();
		
		Player player = new Player(new Vector2(), 10f, 3, 0, 100);
		player.addListener(listener);
		RandomTeleport powerup = new RandomTeleport(new Vector2());
		
		assertEquals(0, powerup.getDuration(), 0.001);
		assertEquals(5, RandomTeleport.getFrequency());	
		
		assertEquals(PowerUpType.RANDOM_TELEPORT, powerup.getPowerUpType());	
		
		powerup.activateEffect(player);
		assertTrue(listener.done);
	}
	
}
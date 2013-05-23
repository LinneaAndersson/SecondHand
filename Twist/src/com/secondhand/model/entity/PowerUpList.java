package com.secondhand.model.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.BlackColor;

public final class PowerUpList extends ArrayList<IPowerUp> {

	private static final long serialVersionUID = 1L;
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public static final String ADD_POWERUP = "addPowerUp";
	
	private final Player player;
	
	public PowerUpList(final Player player) {
		super();
		this.player = player;
	}

	@Override
	public boolean add(final IPowerUp powerUp) {
		powerUp.activatePowerUp(this.player, this.hasAnother(new BlackColor(new Vector2())));
		pcs.firePropertyChange(ADD_POWERUP, null, powerUp);
		return super.add(powerUp);
	}
	
	@Override
	public boolean remove(final Object object) {
		final boolean value = super.remove(object); // Priority: The list is
		// empty when you remove
		// last PowerUp
		final IPowerUp powerUp = (IPowerUp) object;
		// out of powerups? then reset player color altogether. 
		if(this.size() == 0) {
			powerUp.resetPlayerColor(player);
		}
		powerUp.deactivateEffect(player, hasAnother(powerUp));
		return value;
	}

	public void addListener(final PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removeListener(final PropertyChangeListener observer) {
		this.pcs.removePropertyChangeListener(observer);
	}

	
	public boolean hasAnother(final IPowerUp powerUp) {
		boolean hasAnother = false;
		for (final IPowerUp otherPowerUp : this) {
			if (otherPowerUp.getClass() == powerUp.getClass()) 
				hasAnother = true;
		}
		
		return hasAnother;	
	}
}

package com.secondhand.model.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public final class PowerUpList extends ArrayList<IPowerUp> {

	private static final long serialVersionUID = 1L;
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public static final String ADD_POWERUP = "addPowerUp";
	
	private Player player;

	@Override
	public boolean add(final IPowerUp powerUp) {
		powerUp.activatePowerUp(this.player);
		pcs.firePropertyChange(ADD_POWERUP, null, powerUp);
		return super.add(powerUp);
	}
	
	public void setPlayer(final Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	@Override
	public boolean remove(final Object object) {
		final boolean value = super.remove(object); // Priority: The list is
		// empty when you remove
		// last PowerUp
		final IPowerUp powerUp = (IPowerUp) object;
		powerUp.resetPlayerColor();
		powerUp.deactivateEffect(hasAnother(powerUp));
		return value;
	}

	public void addListener(final PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
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

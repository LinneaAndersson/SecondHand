package com.secondhand.model.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.secondhand.debug.MyDebug;

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
		MyDebug.d("deactivating powerup:" + powerUp + " another:" + hasAnother(powerUp));
		
		// out of powerups? then reset player color altogether. 
		if(this.size() == 0) {
			MyDebug.d("restoring player color");
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
	

	@Override
	protected void finalize() throws Throwable 
	{
		try
		{
			MyDebug.i("poweruplist destroyed : " + this.toString());
		}
		finally
		{
			super.finalize();
		}
	}	
}

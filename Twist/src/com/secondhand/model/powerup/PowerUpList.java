package com.secondhand.model.powerup;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.entity.IPowerUp;
import com.secondhand.model.entity.Player;

public class PowerUpList extends ArrayList<IPowerUp> {

	
	// NOT FINISHED!
	
	private static final long serialVersionUID = 1L;
	private static PowerUpList instance;
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public static final String ADD_POWERUP = "addPowerUp";
	
	public PowerUpList() {
		super();
	}

	@Override
	public boolean add(IPowerUp powerUp) {
		pcs.firePropertyChange(ADD_POWERUP, null, powerUp);
		return super.add(powerUp);
	}
	
	@Override
	public boolean remove(final Object object) {
		final boolean value = super.remove(object); // Priority: The list is
		// empty when you remove
		// last PowerUp
		((IPowerUp) object).deactivateEffect();
		return value;
	}

	public static PowerUpList getInstance(){
		if(instance==null){
			instance = new PowerUpList();
		}
		return instance;
	}

	public void addListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
}

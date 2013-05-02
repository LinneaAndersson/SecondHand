package com.secondhand.view.Entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.secondhand.view.resource.Sounds;

// If other Entities will implement PropertyChangeListener, 
// we may want to make IEntityView extends PropertyChangeListener
public class PlayerView implements IEntityView, PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		String propertyName = arg0.getPropertyName();
		if (propertyName.equals("PowerUpSound")) {
			Sounds.getInstance().powerUpSound.play();
		}
	}

}

package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.view.resource.Sounds;

public class PlayerView implements IEntityView, PropertyChangeListener {

	private GameWorld gameWorld;
	
	public PlayerView(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		
		this.gameWorld.getPlayer().addListener(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String propertyName = event.getPropertyName();
		if (propertyName.equals(Player.POWER_UP_SOUND)) {
			Sounds.getInstance().powerUpSound.play();
		}
	}

}

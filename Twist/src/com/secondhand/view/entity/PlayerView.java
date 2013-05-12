package com.secondhand.view.entity;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.Player;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.view.resource.Sounds;

// what? the controller should be handling the PropertyChangeListener, not the view!
//The view can be a propertychangeListener
public class PlayerView extends BlackHoleView {

	public PlayerView(final PhysicsWorld physicsWorld, final Player player){
		super(physicsWorld, player);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		super.propertyChange(event);
		
		final String propertyName = event.getPropertyName();
		

		if (propertyName.equalsIgnoreCase("sound")) {
			
			if(Sounds.getInstance().getPlayerSound(((String)event.getNewValue())) != null){
			Sounds.getInstance().getPlayerSound((String)(event.getNewValue())).play();
			}
			
		}else if (  propertyName.equals(Player.ADD_POWER_UP)) {
			
			Sounds.getInstance().getPlayerSound(Player.ADD_POWER_UP).play();
			final PowerUp powerUp = ((PowerUp) event.getNewValue());
			if(powerUp.getDuration() != 0)
				this.shape.setColor(powerUp.getR(), powerUp.getG(), powerUp.getB());
			
		} else if (propertyName.equals(Player.REMOVE_POWER_UP)) {	
			
			//if player has no other powerup, then reset player color.
			final Player player = (Player)event.getSource();
			if(player.getPowerUps().size() == 0)
				this.shape.setColor(1f, 1f, 1f);
		
		}
	}
}

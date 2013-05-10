package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.Player;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.view.resource.Sounds;
import com.secondhand.view.resource.TextureRegions;

// what? the controller should be handling the PropertyChangeListener, not the view!
//The view can be a propertychangeListener
public class PlayerView extends BlackHoleView {

	public PlayerView(final PhysicsWorld physicsWorld, final Player player){
		super(physicsWorld, player, TextureRegions.getInstance().playerSprite);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		super.propertyChange(event);

		final String propertyName = event.getPropertyName();

		if (propertyName.equals(Player.POWER_UP_SOUND)) {
			Sounds.getInstance().powerUpSound.play();
		}else if (  propertyName.equals(Player.ADD_POWER_UP)) {
			
			MyDebug.d("now change player color");
			//final Player player = gameWorld.getPlayer();
			final PowerUp powerUp = ((PowerUp) event.getNewValue());
			//engine.registerUpdateHandler(createTimer(player, powerUp));
			
			if(powerUp.getDuration() != 0)
				this.shape.setColor(powerUp.getR(), powerUp.getG(), powerUp.getB());
		} else if (propertyName.equals(Player.REMOVE_POWER_UP)) {
			// reset player color. 
			// TODO:if player has no other powerup, then reset player color.
			// we'll do this for now. 
			this.shape.setColor(1f, 1f, 1f);
		} else if (propertyName.equals(Player.GROW_SOUND)) {
			Sounds.getInstance().growSound.play();
		} else if (propertyName.equals(Player.BIGGER_ENTITY_COLLISION_SOUND)) {
			Sounds.getInstance().obstacleCollisionSound.play();
		}  else if (propertyName.equals(Player.PLAYER_KILLED_SOUND)) {
			Sounds.getInstance().playerKilledSound.play();
		}
	}
}

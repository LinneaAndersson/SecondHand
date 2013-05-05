package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.particle.ParticleSystem;
import org.anddev.andengine.entity.particle.emitter.PointParticleEmitter;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Entity;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.view.loader.TextureRegionLoader;
import com.secondhand.view.resource.Sounds;

// what? the controller should be handling the PropertyChangeListener, not the view!
public class PlayerView extends BlackHoleView {

	private Engine engine;
	private GameWorld gameWorld;
	
	public PlayerView(final Player player){
		super(player);
	}
	
	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		final String propertyName = event.getPropertyName();
		final Player player =  (Player) getEntity();
		
		if(propertyName.equalsIgnoreCase("Radius")){
			changeSize();
		}else if (propertyName.equals(Player.POWER_UP_SOUND)) {
			Sounds.getInstance().powerUpSound.play();
		}else if (propertyName.equals(Player.ADD_POWER_UP)) {
			player.getShape().setColor(1f, 0, 0);
		/*} else if (propertyName.equals(Player.REMOVE_POWER_UP)) {
			if (player.getPowerUps().isEmpty()) {
				player.getShape().setColor(1f, 1f, 1f);
			}*/
		} else if (propertyName.equals(Player.GROW_SOUND)) {
			Sounds.getInstance().growSound.play();
		} else if (propertyName.equals(Player.BIGGER_ENTITY_COLLISION_SOUND)) {
			Sounds.getInstance().obstacleCollisionSound.play();
		}  else if (propertyName.equals(Player.PLAYER_KILLED_SOUND)) {
			Sounds.getInstance().playerKilledSound.play();
		}
	}

}

package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.secondhand.model.GameWorld;
import com.secondhand.model.Planet;
import com.secondhand.model.Player;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.view.opengl.Circle;
/*
public class PowerUpView extends EntityView{

	private final Engine engine;
	private final GameWorld gameWorld;
	
	public PowerUpView(final Engine engine, final GameWorld gameWorld) {
		//just for now. 
		super(null , new Circle(0,0,0));
		this.engine = engine;
		this.gameWorld = gameWorld;
	}
	
	public TimerHandler createTimer(final Player player, final PowerUp powerUp) {
		return new TimerHandler(powerUp.getDuration(), new ITimerCallback() {
			private Player user = player; 
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (user.getPowerUps().contains(powerUp))
					user.removePowerUp(powerUp);
				//if (!powerUp.hasAnother(player)) {
					// TODO: Unattach the powerups texture from player (ex: shield makes the player glow)
				//}
			}
		});
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		final String eventName = event.getPropertyName();
		if (eventName.equals(Player.ADD_POWER_UP)) {

			final Player player = gameWorld.getPlayer();
			final PowerUp powerUp = ((PowerUp) event.getNewValue());
			engine.registerUpdateHandler(createTimer(player, powerUp));
//	
			if (!powerUp.hasAnother(player)) {
				// TODO: Attach the powerups texture to player (ex: shield makes the player glow)
			//}
			
			// TODO: Implement floating text here
//			if (powerUp.hasText()) {
//				showFadingTextNotifier(powerUp.getText(),
//						new Vector2(player.getX(), player.getY()));
		}
	}
	
}
*/
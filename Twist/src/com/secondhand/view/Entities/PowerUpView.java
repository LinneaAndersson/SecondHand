package com.secondhand.view.Entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.model.powerup.PowerUp;

public class PowerUpView implements IEntityView, PropertyChangeListener {

	private Engine engine;
	private GameWorld gameWorld;
	
	public PowerUpView(Engine engine, GameWorld gameWorld) {
		this.engine = engine;
		this.gameWorld = gameWorld;
	}
	
	public TimerHandler getTimer(final Player player, final PowerUp powerUp) {
		return new TimerHandler(powerUp.getDuration(), new ITimerCallback() {
			private Player user = player; 
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (user.getPowerUps().contains(powerUp))
					user.removePowerUp(powerUp);
			}
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String eventName = event.getPropertyName();
		if (eventName.equals("AddPowerUp")) {

			final Player player = gameWorld.getPlayer();
			final PowerUp powerUp = ((PowerUp) event.getNewValue());
			engine.registerUpdateHandler(getTimer(player, powerUp));

			// TODO: Implement floating text here
//			if (powerUp.hasText()) {
//				showFadingTextNotifier(powerUp.getText(),
//						new Vector2(player.getX(), player.getY()));
		}
	}
	
}

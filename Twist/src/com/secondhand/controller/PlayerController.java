package com.secondhand.controller;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.secondhand.model.entity.Player;
import com.secondhand.model.powerup.PowerUp;

public final class PlayerController {
	
	private PlayerController() {}

	public static TimerHandler createTimer(final Player player, final PowerUp powerUp) {
		return new TimerHandler(powerUp.getDuration(), new ITimerCallback() {
			private Player user = player; 
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (user.getPowerUps().contains(powerUp))
					user.removePowerUp(powerUp);
			}
			/*//PowerUp will take care of everything that has to do with PowerUps.
			private PowerUp thisPowerUp = powerUp; 
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (( thisPowerUp.getPowerUps()).contains(powerUp))
					thisPowerUp.removePowerUp(powerUp);
			}*/
		});
	}
}

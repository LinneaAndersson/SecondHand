package com.secondhand.controller.model;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.Player;
import com.secondhand.model.powerup.PowerUp;

public final class PlayerController {
	
	private PlayerController() {}

	public static TimerHandler createTimer(final Player player, final PowerUp powerUp) {
		MyDebug.d("powerup duration: " + powerUp.getDuration());
		return new TimerHandler(powerUp.getDuration(), new ITimerCallback() {
			private Player user = player; 
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				MyDebug.d("now the time has passed");
				if (user.getPowerUps().contains(powerUp))
					user.removePowerUp(powerUp);
				//if (!powerUp.haAnother(player)) {
				// TODO: Unattach the powerups texture from player (ex: shield makes the player glow)
				//}
			}
		});
	}
}

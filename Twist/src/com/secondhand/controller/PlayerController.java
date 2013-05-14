package com.secondhand.controller;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.entity.Player;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.model.powerup.PowerUpList;

public final class PlayerController {
	private static PowerUp thisPowerUp;
	private PlayerController() {}

	public static TimerHandler createTimer(final Player player) {
		thisPowerUp = (PowerUp) PowerUpList.getInstance().get(0); 
		return new TimerHandler(thisPowerUp.getDuration(), new ITimerCallback() {
			/*private Player user = player; 
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (user.getPowerUps().contains(powerUp))
					user.removePowerUp(powerUp);
			}*/
			//PowerUp will take care of everything that has to do with PowerUps.
			
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (thisPowerUp.getPowerUps().contains(thisPowerUp)){
					MyDebug.d("in playerControll with PowerUp:" + thisPowerUp);
					thisPowerUp.removePowerUp();
				}
			}
		});
	}
}

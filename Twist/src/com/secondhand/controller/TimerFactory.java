package com.secondhand.controller;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.entity.Player;
import com.secondhand.model.entity.PowerUpList;
import com.secondhand.model.powerup.PowerUp;

public final class TimerFactory {
	
	private TimerFactory() {}

	public static TimerHandler createTimer(final PowerUp powerUp) { 
		return new TimerHandler(powerUp.getDuration(), new ITimerCallback() {
			private PowerUp timerPowerUp = powerUp;
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (PowerUpList.getInstance().contains(timerPowerUp)){
					MyDebug.d("in playerControll with PowerUp:" + timerPowerUp);
					PowerUpList.getInstance().remove(timerPowerUp);
				}
			}
		});
	}
}

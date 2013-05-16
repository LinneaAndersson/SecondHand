package com.secondhand.controller;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.powerup.PowerUp;

public final class TimerFactory {
	
	private TimerFactory() {}

	public static TimerHandler createTimer(final IGameWorld gameWorld, final PowerUp powerUp) { 
		return new TimerHandler(powerUp.getDuration(), new ITimerCallback() {
			private PowerUp timerPowerUp = powerUp;
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (gameWorld.getPowerUpList().contains(timerPowerUp)){
					MyDebug.d("in playerControll with PowerUp:" + timerPowerUp);
					gameWorld.getPowerUpList().remove(timerPowerUp);
				}
			}
		});
	}
}

package com.secondhand.controller;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.particle.ParticleSystem;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.view.andengine.entity.RocketEmitter;
import com.secondhand.view.scene.GamePlayScene;

public final class TimerFactory {
	
	private TimerFactory() {}

	public static TimerHandler createTimer(final SceneManager handler, final IGameWorld gameWorld, final PowerUp powerUp) { 
		return new TimerHandler(powerUp.getDuration(), new ITimerCallback() {
			private PowerUp timerPowerUp = powerUp;
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (gameWorld.getPowerUpList().contains(timerPowerUp)){
					MyDebug.d("in playerControll with PowerUp:" + timerPowerUp);
					gameWorld.getPowerUpList().remove(timerPowerUp);
				}
				handler.unregisterUpdateHandler(pTimerHandler);
			}
		});
	}
	
	public static TimerHandler createRocketTimer(final SceneManager handler, final GamePlayScene scene, final RocketEmitter emitter) {
		return new TimerHandler(RocketEmitter.DURATION, new ITimerCallback() {			
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				scene.detachRocketEmitter(emitter);
				handler.unregisterUpdateHandler(pTimerHandler);
			}
		});
	}
	
}

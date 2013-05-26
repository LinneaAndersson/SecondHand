package com.secondhand.controller;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.view.andengine.entity.RocketEmitter;
import com.secondhand.view.scene.GamePlayScene;

/* Singleton class for creating various timers. */
public final class TimerFactory {

	private TimerFactory() {
	}
	
	/* PowerUp timer */
	public static TimerHandler createTimer(final SceneManager handler,
			final IGameWorld gameWorld, final PowerUp powerUp) {
		return new TimerHandler(powerUp.getDuration(), new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (gameWorld.getPowerUpList().contains(powerUp)) {
					gameWorld.getPowerUpList().remove(powerUp);
				}
				handler.unregisterUpdateHandler(pTimerHandler);
			}
		});
	}

	/* RocketParticleSystem timer */
	public static TimerHandler createRocketTimer(final SceneManager handler,
			final GamePlayScene scene, final RocketEmitter emitter) {
		return new TimerHandler(RocketEmitter.DURATION, new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				scene.detachRocketEmitter(emitter);
				handler.unregisterUpdateHandler(pTimerHandler);
			}
		});
	}

}

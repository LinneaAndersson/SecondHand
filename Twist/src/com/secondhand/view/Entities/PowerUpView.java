package com.secondhand.view.Entities;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;

import com.secondhand.model.Player;
import com.secondhand.model.powerup.PowerUp;

public class PowerUpView implements IEntityView{

	public TimerHandler getTimer(final Player player, final PowerUp powerUp, final float duration) {
		return new TimerHandler(duration, new ITimerCallback() {
			private Player user = player; 
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if (user.getPowerUps().contains(powerUp))
					user.removePowerUp(powerUp);
			}
		});
	}
	
}

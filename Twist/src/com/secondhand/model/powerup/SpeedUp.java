package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Level;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class SpeedUp extends PowerUp {

	private final static float DURATION = 10;
	private int factor = 3;
	
	public SpeedUp(final Vector2 position, 
			final Level level) {
		super(position, PowerUpType.SPEED_UP, level, DURATION);
		
	}

	public int getFactor() {
		return factor;
	}
	
	public void setFactor(final int newFactor) {
		factor = newFactor;
	}
	
	@Override
	public void activateEffect(final Player player) {
		player.getCircle().setColor(0, 0, 1f);
		player.setMaxSpeed(player.getMaxSpeed()*factor);
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		super.deactivateEffect(player);
		player.setMaxSpeed(player.getMaxSpeed()/factor);
	}
}

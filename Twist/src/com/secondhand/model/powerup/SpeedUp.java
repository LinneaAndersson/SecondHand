package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;

public class SpeedUp extends PowerUp {

	private final static float DURATION = 10;
	private int factor = 3;
	
	public SpeedUp(final Vector2 position, 
			final GameWorld level) {
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
		MyDebug.d("applying speed up");
		player.getCircle().setColor(0, 0, 1f);
		player.setMaxSpeed(player.getMaxSpeed()*factor);
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		

		final boolean hasAnother = super.hasAnother(player);
		
		if(!hasAnother)
			super.deactivateEffect(player);
		
		if(!hasAnother)
			player.setMaxSpeed(player.getMaxSpeed()/factor);
	}
}

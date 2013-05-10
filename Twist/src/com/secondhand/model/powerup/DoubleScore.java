package com.secondhand.model.powerup;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.model.Vector2;
import com.secondhand.model.resource.PowerUpType;

// TOOD: this does not yet work for the ScoreUp powerup, fix this.
public class DoubleScore extends PowerUp {
	
	private final static float DURATION = 10;
	
	private final static float MULT = 2;
	
	public DoubleScore(final Vector2 position,
			final GameWorld level) {
		super(position, PowerUpType.DOUBLE_SCORE, level, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
		MyDebug.d("activate double score");
		player.setScoreMultiplier(2);
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		MyDebug.d("deactivate double score");
		final boolean hasAnother = super.hasAnother(player);
		
		player.setScoreMultiplier(hasAnother ? MULT : 1);
	}
	
	public float getR() {return 1f;}
	public float getG() {return 1f;}
	public float getB() {return 0f;}
}

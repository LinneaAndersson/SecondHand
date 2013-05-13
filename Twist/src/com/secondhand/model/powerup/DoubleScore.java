package com.secondhand.model.powerup;

import com.secondhand.model.entity.GameWorld;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

// TODO: this does not yet work for the ScoreUp powerup, fix this.
public class DoubleScore extends PowerUp {
	
	private final static float DURATION = 10;
	
	private final static float MULT = 2;
	
	public DoubleScore(final Vector2 position, Player player) {
		super(position, PowerUpType.DOUBLE_SCORE, DURATION, player);
	}

	@Override
	public void activateEffect(final Player player) {
		player.setScoreMultiplier(2);
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		final boolean hasAnother = super.hasAnother(player);
		
		player.setScoreMultiplier(hasAnother ? MULT : 1);
	}
	
	public float getR() {return 1f;}
	public float getG() {return 1f;}
	public float getB() {return 0f;}
}

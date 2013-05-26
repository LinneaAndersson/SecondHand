package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class DoubleScore extends PowerUp {

	private final static float DURATION = 10;

	private final static float MULT = 2;

	public DoubleScore(final Vector2 position) {
		super(position, PowerUpType.DOUBLE_SCORE, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
		player.setScoreMultiplier(2);
	}

	@Override
	public void deactivateEffect(final Player player, final boolean hasAnother) {
		player.setScoreMultiplier(hasAnother ? MULT : 1);
	}

	public float getR() {
		return 1f;
	}

	public float getG() {
		return 1f;
	}

	public float getB() {
		return 0f;
	}

	public static int getFrequency() {
		return 5;
	}
}

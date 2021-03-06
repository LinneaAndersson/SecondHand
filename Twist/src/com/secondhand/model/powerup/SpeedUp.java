package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class SpeedUp extends PowerUp {

	private final static float DURATION = 10;
	private final static int FACTOR = 2;

	public SpeedUp(final Vector2 position) {
		super(position, PowerUpType.SPEED_UP, DURATION);

	}

	@Override
	public void activateEffect(final Player player) {
		player.setSpeedMultiplier(player.getSpeedMultiplier() * FACTOR);
	}

	@Override
	public void deactivateEffect(final Player player, final boolean hasAnother) {
		player.setSpeedMultiplier(player.getSpeedMultiplier() / FACTOR);
	}

	public float getR() {
		return 0f;
	}

	public float getG() {
		return 0f;
	}

	public float getB() {
		return 1f;
	}

	public static int getFrequency() {
		return 6;
	}
}

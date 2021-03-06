package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class Shield extends PowerUp {

	private final static float DURATION = 4;

	public Shield(final Vector2 position) {
		super(position, PowerUpType.SHIELD, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
		player.setIsEdible(false);
	}

	@Override
	public void deactivateEffect(final Player player, final boolean hasAnother) {
		player.setIsEdible(!hasAnother);
	}

	public float getR() {
		return 0f;
	}

	public float getG() {
		return 1f;
	}

	public float getB() {
		return 0f;
	}

	public static int getFrequency() {
		return 4;
	}

}

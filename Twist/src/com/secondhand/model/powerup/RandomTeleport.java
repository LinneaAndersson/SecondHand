package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class RandomTeleport extends PowerUp {

	private final static float DURATION = 0;

	public RandomTeleport(final Vector2 position) {
		super(position, PowerUpType.RANDOM_TELEPORT, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
		player.moveToRandomUnoccupiedArea();
	}

	public static int getFrequency() {
		return 5;
	}

}

package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Level;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class EatObstacle extends PowerUp {

	private final static float DURATION = 5;
	
	public EatObstacle(final Vector2 position,
			final Level level) {
		super(position, PowerUpType.EAT_OBSTACLE, level, DURATION);

	}

	@Override
	public void activateEffect(final Player player) {
		player.getCircle().setColor(1f, 0, 0);
	}
}

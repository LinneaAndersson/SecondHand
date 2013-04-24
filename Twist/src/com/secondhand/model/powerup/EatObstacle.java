package com.secondhand.model.powerup;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class EatObstacle extends PowerUp {

	private final static float DURATION = 5;
	
	public EatObstacle(final Vector2 position,
			final PhysicsWorld physicsWorld) {
		super(position, PowerUpType.EAT_OBSTACLE, physicsWorld, DURATION);

	}

	@Override
	public void activateEffect(final Player player) {
		player.getCircle().setColor(1f, 0, 0);
	}
}

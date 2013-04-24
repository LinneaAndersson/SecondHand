package com.secondhand.model.powerup;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class ScoreUp extends PowerUp {

	private final static float DURATION = 0;
	
	public ScoreUp(final Vector2 position,
			final PhysicsWorld physicsWorld) {
		super(position, PowerUpType.SCORE_UP, physicsWorld, DURATION);

	}

	@Override
	public void activateEffect(final Player player) {

	}

	@Override
	public void deactivateEffect(final Player player) {

	}

}

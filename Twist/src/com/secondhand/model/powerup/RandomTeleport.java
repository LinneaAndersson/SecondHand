package com.secondhand.model.powerup;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class RandomTeleport extends PowerUp {

	private final static float DURATION = 0;
	
	public RandomTeleport(final Vector2 position, 
			final PhysicsWorld physicsWorld) {
		super(position, PowerUpType.RANDOM_TELEPORT, physicsWorld, DURATION);

	}

	@Override
	public void activateEffect(final Player player) {
		
		final Body body = player.getBody();
		player.setNeedsToMovePosition(new Vector2(body.getPosition().x + 700f/32f, player.getBody().getPosition().y));
	}

	@Override
	public void deactivateEffect(final Player player) {

	}

}

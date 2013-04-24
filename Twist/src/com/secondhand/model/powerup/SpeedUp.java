package com.secondhand.model.powerup;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class SpeedUp extends PowerUp {

	private final static float DURATION = 2;
	
	public SpeedUp(final Vector2 position, 
			final PhysicsWorld physicsWorld) {
		super(position, PowerUpType.SPEED_UP, physicsWorld, DURATION);
		
	}

	@Override
	public void activateEffect(final Player player) {
		player.getCircle().setColor(0, 0, 1f);
	}
}

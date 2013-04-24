package com.secondhand.model.powerup;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class RandomTeleport extends PowerUp {

	private final static float DURATION = 0;
	
	public RandomTeleport(Vector2 position, 
			PhysicsWorld physicsWorld) {
		super(position, PowerUpType.RANDOM_TELEPORT, physicsWorld, DURATION);

	}

	@Override
	public void activateEffect(Player player) {
		
	}

	@Override
	public void deactivateEffect(Player player) {

	}

}

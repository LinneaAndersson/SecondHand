package com.secondhand.model.powerup;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class SpeedUp extends PowerUp {

	private final static float DURATION = 2;
	
	public SpeedUp(Vector2 position, 
			PhysicsWorld physicsWorld) {
		super(position, PowerUpType.SPEED_UP, physicsWorld, DURATION);
		
	}

	@Override
	public void activateEffect(Player player) {
		player.getCircle().setColor(0, 0, 1f);
	}

	@Override
	public void deactivateEffect(Player player) {

	}

}

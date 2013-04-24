package com.secondhand.model.powerup;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class EatObstacle extends PowerUp {

	private final static float DURATION = 5;
	
	public EatObstacle(Vector2 position,
			PhysicsWorld physicsWorld) {
		super(position, PowerUpType.EAT_OBSTACLE, physicsWorld, DURATION);

	}

	@Override
	public void activateEffect(Player player) {
		player.getCircle().setColor(1f, 0, 0);
	}

	@Override
	public void deactivateEffect(Player player) {
		
	}

}

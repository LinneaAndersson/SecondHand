package com.secondhand.model.powerup;

import java.util.Random;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Player;
import com.secondhand.physics.PhysicsAreaChecker;
import com.secondhand.resource.PowerUpType;

public class RandomTeleport extends PowerUp {

	private final static float DURATION = 0;
	
	private final int levelWidth;
	private final int levelHeight;
	
	public RandomTeleport(final Vector2 position, 
			final PhysicsWorld physicsWorld, final int levelWidth, final int levelHeight) {
		super(position, PowerUpType.RANDOM_TELEPORT, physicsWorld, DURATION);

		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
	}

	@Override
	public void activateEffect(final Player player) {
		

		final Random rng = new Random();
		float x,y;
		final float r = player.getRadius();
		
		while(true) {
			
			x = rng.nextInt(levelWidth);
			y = rng.nextInt(levelHeight);
			
			if(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(x - r, y+r), new Vector2(x+r, y-r), physicsWorld))
				break;
		}
			
		player.setNeedsToMovePosition(new Vector2(x / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
				y/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT));
	}

	@Override
	public void deactivateEffect(final Player player) {

	}

}

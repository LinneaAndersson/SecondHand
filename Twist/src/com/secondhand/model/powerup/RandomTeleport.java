package com.secondhand.model.powerup;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.physics.PhysicsAreaChecker;
import com.secondhand.resource.PowerUpType;

public class RandomTeleport extends PowerUp {

	private final static float DURATION = 0;
	
	
	public RandomTeleport(final Vector2 position, 
			final GameWorld level) {
		super(position, PowerUpType.RANDOM_TELEPORT, level, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
		

		final Random rng = new Random();
		float x,y;
		final float r = player.getRadius();
		
		while(true) {
			
			x = rng.nextInt(level.getLevelWidth());
			y = rng.nextInt(level.getLevelHeight());
			
			if(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(x - r, y+r), new Vector2(x+r, y-r), physicsWorld))
				break;
		}
			
		player.setNeedsToMovePosition(new Vector2(x,
				y));
	}

	@Override
	public void deactivateEffect(final Player player) {

	}

}

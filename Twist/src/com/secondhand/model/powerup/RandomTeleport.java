package com.secondhand.model.powerup;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.model.resource.PowerUpType;

public class RandomTeleport extends PowerUp {

	private final static float DURATION = 0;
	
	
	public RandomTeleport(final Vector2 position, 
			final GameWorld level) {
		super(position, PowerUpType.RANDOM_TELEPORT, level, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
		MyDebug.d("applying random teleport");

		final Random rng = new Random();
		float x,y;
		final float r = player.getRadius();
		
		while(true) {
			
			x = rng.nextInt(level.getLevelWidth());
			y = rng.nextInt(level.getLevelHeight());
			
			if(physics.isAreaUnOccupied(x, y, r))
				break;
		}
			
		player.setNeedsToMovePosition(new Vector2(x,
				y));
	}

	@Override
	public void deactivateEffect(final Player player) {

	}

}

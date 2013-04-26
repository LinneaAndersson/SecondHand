package com.secondhand.model.powerup;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Level;
import com.secondhand.model.Player;
import com.secondhand.physics.PhysicsAreaChecker;
import com.secondhand.resource.PowerUpType;

public class RandomTeleport extends PowerUp {

	private final static float DURATION = 0;
	
	private final int levelWidth;
	private final int levelHeight;
	
	public RandomTeleport(final Vector2 position, 
			final Level level) {
		super(position, PowerUpType.RANDOM_TELEPORT, level, DURATION);

		this.levelWidth = level.getLevelWidth();
		this.levelHeight = level.getLevelHeight();
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
			
		player.setNeedsToMovePosition(new Vector2(x,
				y));
	}

	@Override
	public void deactivateEffect(final Player player) {

	}

}

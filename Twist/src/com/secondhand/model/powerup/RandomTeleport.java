package com.secondhand.model.powerup;

import java.util.Random;

import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class RandomTeleport extends PowerUp {

	private final static float DURATION = 0;
	private final IGameWorld gameWorld;
	
	
	public RandomTeleport(final Vector2 position, 
			final IGameWorld level) {
		super(position, PowerUpType.RANDOM_TELEPORT, DURATION);
		this.gameWorld = level;
	}

	@Override
	public void activateEffect(final Player player) {
	
		final Random rng = new Random();
		
		final float r = player.getRadius();
		
			
		player.setNeedsToMovePosition(this.gameWorld.getPhysics().getRandomUnOccupiedArea(
				this.gameWorld.getLevelWidth(),
				this.gameWorld.getLevelHeight(),
				r, rng));
	}

	
	public static int getFrequency() { return 5; }

}

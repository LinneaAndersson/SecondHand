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
			final IGameWorld level,Player player) {
		super(position, PowerUpType.RANDOM_TELEPORT, DURATION, player);
		this.gameWorld = level;
	}

	@Override
	public void activateEffect(final Player player) {
	
		final Random rng = new Random();
		float x,y;
		final float r = player.getRadius();
		
		while(true) {
			
			x = rng.nextInt(gameWorld.getLevelWidth());
			y = rng.nextInt(gameWorld.getLevelHeight());
			
			if(this.gameWorld.getPhysics().isAreaUnOccupied(x, y, r))
				break;
		}
			
		player.setNeedsToMovePosition(new Vector2(x,
				y));
	}

	
	@Override
	public int getRarity() { return 5; }

}

package com.secondhand.model.powerup;

import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.model.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class EatObstacle extends PowerUp {

	private final static float DURATION = 5;
	
	public EatObstacle(final Vector2 position,
			final GameWorld level) {
		super(position, PowerUpType.EAT_OBSTACLE, level, DURATION);

	}

	@Override
	public void activateEffect(final Player player) {
		/*
		player.setCanEatInedibles(true);*/
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		final boolean hasAnother = super.hasAnother(player);
		
		if(!hasAnother)
			super.deactivateEffect(player);
		
		player.setCanEatInedibles(!hasAnother);
	}
	
	public float getR() {return 1f;}
	public float getG() {return 0f;}
	public float getB() {return 0f;}
}

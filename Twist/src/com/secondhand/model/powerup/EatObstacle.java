package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class EatObstacle extends PowerUp {

	private final static float DURATION = 5;
	
	public EatObstacle(final Vector2 position) {
		super(position, PowerUpType.EAT_OBSTACLE, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
		player.setCanEatInedibles(true);
	}
	
	@Override
	public void deactivateEffect(final boolean hasAnother) {
		player.setCanEatInedibles(!hasAnother);
	}
	
	public float getR() {return 1f;}
	public float getG() {return 0f;}
	public float getB() {return 0f;}

	public static int getFrequency() { return 3; }

}


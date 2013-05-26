package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class MirroredMovement extends PowerUp {

	private final static float DURATION = 5;
	
	public MirroredMovement(final Vector2 position) {
		super(position, PowerUpType.MIRRORED_MOVEMENT, DURATION);

	}

	@Override
	public void activateEffect(final Player player) {
		player.setMirroredMovement(true);
	}
	
	@Override
	public void deactivateEffect(final Player player, final boolean hasAnother) {
		player.setMirroredMovement(hasAnother);
	}

	public float getR() {return 1f;}
	public float getG() {return 0f;}
	public float getB() {return 1f;}

	public static int getFrequency() { return 5; }

}

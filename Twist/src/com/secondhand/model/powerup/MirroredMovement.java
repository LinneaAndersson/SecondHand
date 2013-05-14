package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class MirroredMovement extends PowerUp {

	private final static float DURATION = 5;
	
	public MirroredMovement(final Vector2 position,Player player) {
		super(position, PowerUpType.MIRRORED_MOVEMENT, DURATION, player);

	}

	@Override
	public void activateEffect(final Player player) {
		player.setMirroredMovement(true);
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		final boolean hasAnother = super.hasAnother(player);
		
		player.setMirroredMovement(hasAnother);
	}
	
	@Override
	public void removePowerUp() {
		super.removePowerUp();
		player.setMirroredMovement(false);
	}
	

	public float getR() {return 1f;}
	public float getG() {return 0f;}
	public float getB() {return 1f;}

	// make it common, so that it's more annoying :)
	public static int getFrequency() { return 8; }

}

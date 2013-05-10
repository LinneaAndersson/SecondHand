package com.secondhand.model.powerup;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.model.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class MirroredMovement extends PowerUp {

	private final static float DURATION = 5;
	
	public MirroredMovement(final Vector2 position,
			final GameWorld level) {
		super(position, PowerUpType.MIRRORED_MOVEMENT, level, DURATION);

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
	

	public float getR() {return 1f;}
	public float getG() {return 0f;}
	public float getB() {return 1f;}
}

package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class Shield extends PowerUp {
	
	private final static float DURATION = 4;
	
	public Shield(final Vector2 position,Player player) {
		super(position, PowerUpType.SHIELD, DURATION, player);
	}

	@Override
	public void activateEffect(final Player player) {
		player.setIsEdible(false);
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		
		final boolean hasAnother = super.hasAnother(player);
		
		player.setIsEdible(!hasAnother);
	}
	

	public float getR() {return 0f;}
	public float getG() {return 1f;}
	public float getB() {return 0f;}

	// pretty useful, so it's rare
	@Override
	public int getFrequency() { return 4; }

}

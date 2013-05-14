package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class ExtraLife extends PowerUp {

	private final static float DURATION = 0;
	
	public ExtraLife(final Vector2 position,Player player) {
		super(position, PowerUpType.EXTRA_LIFE, DURATION, player);
	}

	@Override
	public void activateEffect(final Player player) {
		player.gainLife();
	}
	
	@Override
	public String getText(){
		return "1UP";
	}
	
	@Override
	public int getRarity() { return 9; }

}

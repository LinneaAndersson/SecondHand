package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;

public class ExtraLife extends PowerUp {

	private final static float DURATION = 0;
	
	public ExtraLife(final Vector2 position,
			final  GameWorld level) {
		super(position, PowerUpType.EXTRA_LIFE, level, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
		MyDebug.d("applying extra life");
		player.gainLife();
	}
	
	@Override
	public String getText(){
		return "1UP";
	}
}

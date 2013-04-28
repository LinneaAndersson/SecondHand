package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.model.PowerUpType;

public class ExtraLife extends PowerUp {

	private final static float DURATION = 0;
	
	public ExtraLife(final Vector2 position,
			final  GameWorld level) {
		super(position, PowerUpType.EXTRA_LIFE, level, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
		player.gainLife();
		/*if(level.hasView()) {
			level.getView().showFadingTextNotifier("1UP", new Vector2(player.getX(), player.getY()));
		}*/
	}
}

package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;

public class Shield extends PowerUp {
	
	private final static float DURATION = 4;
	
	public Shield(final Vector2 position,
			final GameWorld level) {
		super(position, PowerUpType.SHIELD, level, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
		MyDebug.d("applying score up");
		player.getCircle().setColor(0, 1f, 0);
		player.setIsEdible(false);
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		boolean hasAnotherShield = false;
		for (final PowerUp powerUp : player.getPowerUps()) {
			if (powerUp.getClass() == Shield.class)
				hasAnotherShield = true;
		}
		
		if(!hasAnotherShield)
			super.deactivateEffect(player);
		
		
		player.setIsEdible(!hasAnotherShield);
	}
}

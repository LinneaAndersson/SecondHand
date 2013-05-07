package com.secondhand.model.powerup;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.model.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class Shield extends PowerUp {
	
	private final static float DURATION = 4;
	
	public Shield(final Vector2 position,
			final GameWorld level) {
		super(position, PowerUpType.SHIELD, level, DURATION);
	}

	/*@Override
	public void activateEffect(final Player player) {
		MyDebug.d("applying score up");
		player.getCircle().setColor(0, 1f, 0);
		player.setIsEdible(false);
	}*/
	
	@Override
	public void deactivateEffect(final Player player) {
		
		final boolean hasAnother = super.hasAnother(player);
		
		if(!hasAnother)
			super.deactivateEffect(player);
		
		
		player.setIsEdible(!hasAnother);
	}
}

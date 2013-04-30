package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;

public class EatObstacle extends PowerUp {

	private final static float DURATION = 5;
	
	public EatObstacle(final Vector2 position,
			final GameWorld level) {
		super(position, PowerUpType.EAT_OBSTACLE, level, DURATION);

	}

	@Override
	public void activateEffect(final Player player) {
		MyDebug.d("applying eat obstacle");
		player.getCircle().setColor(1f, 0, 0);
		player.setCanEatInedibles(true);
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		boolean anotherOne = false;
		for (final PowerUp powerUp : player.getPowerUps()) {
			if (powerUp.getClass() == EatObstacle.class)
				anotherOne = true;
		}
		
		if(!anotherOne)
			super.deactivateEffect(player);
		
		player.setCanEatInedibles(!anotherOne);
	}
}

package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class ScoreUp extends PowerUp {

	private final static float DURATION = 0;
	private final static int SCORE_BONUS = 10;
	
	public ScoreUp(final Vector2 position,
			final  GameWorld level) {
		super(position, PowerUpType.SCORE_UP, level, DURATION);
	}

	// TODO: should probably used getScoreValue instead here.
	public int getScoreBonus() {
		return SCORE_BONUS;
	}
	
	@Override
	public void activateEffect(final Player player) {
		player.increaseScore(SCORE_BONUS);
		/*if(level.hasView()) {
			level.getView().showFadingTextNotifier(SCORE_BONUS + "+", new Vector2(player.getX(), player.getY()));
		}*/
	}
}

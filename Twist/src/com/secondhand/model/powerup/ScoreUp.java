package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class ScoreUp extends PowerUp {

	private final static float DURATION = 0;
	private final static int SCORE_BONUS = 10;
	
	public ScoreUp(final Vector2 position, final Player player) {
		super(position, PowerUpType.SCORE_UP, DURATION, player);	
	}

	public int getScoreBonus() {
		return SCORE_BONUS;
	}
	
	@Override
	public void activateEffect(final Player player) {
		player.increaseScore(SCORE_BONUS);
	}

	@Override
	public String getText(){
		return (int)(this.player.getScoreMultiplier() * SCORE_BONUS) +"+";
	}

	public static int getFrequency() { return 9; }

}

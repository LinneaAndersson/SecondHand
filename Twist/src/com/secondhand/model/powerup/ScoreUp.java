package com.secondhand.model.powerup;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Level;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class ScoreUp extends PowerUp {

	private final static float DURATION = 0;
	private int scoreBonus = 10;
	
	public ScoreUp(final Vector2 position,
			final  Level level) {
		super(position, PowerUpType.SCORE_UP, level, DURATION);
	}

	// TODO: should probably used getScoreValue instead here.
	public int getScoreBonus() {
		return scoreBonus;
	}
	
	public void setScoreBonus(final int scoreBonus) {
		this.scoreBonus = scoreBonus;
	}
	
	@Override
	public void activateEffect(final Player player) {
		player.increaseScore(scoreBonus);
		if(level.hasView()) {
			level.getView().pickedUpScorePowerUp(scoreBonus, new Vector2(player.getX(), player.getY()));
		}
	}
}

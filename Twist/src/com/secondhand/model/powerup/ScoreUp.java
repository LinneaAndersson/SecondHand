package com.secondhand.model.powerup;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Level;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class ScoreUp extends PowerUp {

	private final static float DURATION = 0;
	private int scoreBonus = 10;
	private Level level;
	
	public ScoreUp(final Vector2 position,
			final PhysicsWorld physicsWorld, Level level) {
		super(position, PowerUpType.SCORE_UP, physicsWorld, DURATION);
		this.level = level;
	}

	public int getScoreBonus() {
		return scoreBonus;
	}
	
	public void setScoreBonus(final int scoreBonus) {
		this.scoreBonus = scoreBonus;
	}
	
	@Override
	public void activateEffect(final Player player) {
		player.increaseScore(scoreBonus);
	}
}

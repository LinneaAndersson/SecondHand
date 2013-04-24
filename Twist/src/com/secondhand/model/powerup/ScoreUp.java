package com.secondhand.model.powerup;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Player;
import com.secondhand.resource.PowerUpType;

public class ScoreUp extends PowerUp {

	private final static float DURATION = 0;
	private int scoreBonus = 10;
	
	public ScoreUp(final Vector2 position,
			final PhysicsWorld physicsWorld) {
		super(position, PowerUpType.SCORE_UP, physicsWorld, DURATION);

	}

	public void setScoreBonus(final int scoreBonus) {
		this.scoreBonus = scoreBonus;
	}
	
	@Override
	public void activateEffect(final Player player) {
		player.increaseScore(scoreBonus);
	}

	@Override
	public void deactivateEffect(final Player player) {

	}

}

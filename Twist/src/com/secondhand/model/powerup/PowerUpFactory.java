package com.secondhand.model.powerup;

import java.util.Random;

import com.secondhand.model.entity.GameWorld;
import com.secondhand.model.physics.Vector2;

// TODO: maybe we should make some powerups, like the ExtraLife powerup, more rare than the others. 
// so we should give them lower frequencies of appearing. 
public final class PowerUpFactory {

	private PowerUpFactory() {}
	
	public static final int NUM_POWER_UPS = 10;

	// used for level generation., 
	public static PowerUp getRandomPowerUp(final Vector2 position, final GameWorld gameWorld, final Random rng) { 
		final int rand = rng.nextInt(NUM_POWER_UPS);
		
		if(rand == 0) {
			return new EatObstacle(position);
		} else if(rand == 1) {
			return new ExtraLife(position);
		}else if(rand == 2) {
			return new RandomTeleport(position, gameWorld);
		}else if(rand == 3) {
			return new ScoreUp(position);
		}else if(rand == 4) {
			return new Shield(position);
		}else if(rand == 5) {
			return new SpeedUp(position);
		} else if(rand == 6) {
			return new DoubleScore(position);
		} else if(rand == 7) {
			return new MirroredMovement(position);
		} else if(rand == 8) {
			return new RandomPowerUp(position, gameWorld);
		}else if(rand == 9) {
			return new SpeedDown(position);
		}
		else
			return null;
	}
}

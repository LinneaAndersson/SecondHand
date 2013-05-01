package com.secondhand.model.powerup;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.GameWorld;

// TODO: maybe we should make some powerups, like the ExtraLife powerup, more rare than the others. 
// so we should give them lower frequencies of appearing. 
public final class PowerUpFactory {

	private PowerUpFactory() {}
	
	public static final int NUM_POWER_UPS = 8;

	// used for level generation., 
	public static PowerUp getRandomPowerUp(final Vector2 position, final GameWorld gameWorld, final Random rng) {
		
		final int rand = rng.nextInt(NUM_POWER_UPS);
		
		
		if(rand == 0) {
			return new EatObstacle(position, gameWorld);
		} else if(rand == 1) {
			return new ExtraLife(position, gameWorld);
		}else if(rand == 2) {
			return new RandomTeleport(position, gameWorld);
		}else if(rand == 3) {
			return new ScoreUp(position, gameWorld);
		}else if(rand == 4) {
			return new Shield(position, gameWorld);
		}else if(rand == 5) {
			return new SpeedUp(position, gameWorld);
		} else if(rand == 6) {
			return new DoubleScore(position, gameWorld);
		} else if(rand == 7) {
			return new MirroredMovement(position, gameWorld);
		}else
			return null;
	}
}

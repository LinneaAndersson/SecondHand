package com.secondhand.model.powerup;

import java.util.Random;

import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;

// TODO: maybe we should make some powerups, like the ExtraLife powerup, more rare than the others. 
// so we should give them lower frequencies of appearing. 
public final class PowerUpFactory {

	private PowerUpFactory() {}
	
	public static final int NUM_POWER_UPS = 10;

	// used for level generation., 
	public static PowerUp getRandomPowerUp(final Vector2 position, final IGameWorld gameWorld, final Random rng, Player player) { 
		final int rand = rng.nextInt(NUM_POWER_UPS);
		
		if(rand == 0) {
			return new EatObstacle(position, player);
		} else if(rand == 1) {
			return new ExtraLife(position, player);
		}else if(rand == 2) {
			return new RandomTeleport(position, gameWorld, player);
		}else if(rand == 3) {
			return new ScoreUp(position, player);
		}else if(rand == 4) {
			return new Shield(position, player);
		}else if(rand == 5) {
			return new SpeedUp(position, player);
		} else if(rand == 6) {
			return new DoubleScore(position, player);
		} else if(rand == 7) {
			return new MirroredMovement(position, player);
		} else if(rand == 8) {
			return new RandomPowerUp(position, gameWorld, player);
		}else if(rand == 9) {
			return new SpeedDown(position, player);
		}
		else
			return null;
	}
}

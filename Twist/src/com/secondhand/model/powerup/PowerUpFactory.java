package com.secondhand.model.powerup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.physics.Vector2;

// Uses the second method described here:
// http://www.electricmonk.nl/log/2009/12/23/weighted-random-distribution/
// to do a Weighted Random Distribution of powerups. 
public final class PowerUpFactory {

	private final List<Weight> weights;
	private final int totalWeight;
	
	public PowerUpFactory() {
		weights = new ArrayList<Weight>();
		
		weights.add(new Weight(1, DoubleScore.getFrequency()));
		weights.add(new Weight(2, EatObstacle.getFrequency()));
		weights.add(new Weight(3, ExtraLife.getFrequency()));
		weights.add(new Weight(4, MirroredMovement.getFrequency()));
		weights.add(new Weight(5, RandomPowerUp.getFrequency()));
		weights.add(new Weight(6, RandomTeleport.getFrequency()));
		weights.add(new Weight(7, ScoreUp.getFrequency()));
		weights.add(new Weight(8, Shield.getFrequency()));
		weights.add(new Weight(9, SpeedDown.getFrequency()));
		weights.add(new Weight(10, SpeedUp.getFrequency()));
		
		
		int tw = 0;
		for(final Weight weight: this.weights) {
			tw += weight.weight;
		}
		this.totalWeight = tw;
	}
	
	
	// used for level generation., 
	public PowerUp getRandomPowerUp(final Vector2 position, final Random rng) { 
		
		// do the Weighted Random Distribution
		final int index = rng.nextInt(totalWeight);
		int s = 0;
		Weight result = null;
		for(final Weight weight: this.weights) {
			s += weight.weight;
			if (s > index) {
				result = weight;
				break;
			}
		}
		
		// now construct the randomized powerup:
		
		return result.constructPowerUp(position);
	}
	
	private class Weight {
		private final int powerUp;
		public int weight;
		
		public Weight(final int powerUp, final int weight) {
			this.powerUp = powerUp;
			this.weight = weight;
		}
		
		public PowerUp constructPowerUp(final Vector2 position) {
			if(powerUp == 1) {
				MyDebug.d("double score");
				return new DoubleScore(position);
			} else if(powerUp == 2) {
				MyDebug.d("eat obstacle");
				return new EatObstacle(position);
			} else if(powerUp == 3) {
				MyDebug.d("extra life");
				return new ExtraLife(position);
			} else if(powerUp == 4) {
				MyDebug.d("mirrored movement");
				return new MirroredMovement(position);
			} else if(powerUp == 5) {
				MyDebug.d("random powerup");
				return new RandomPowerUp(position);
			} else if(powerUp == 6) {
				MyDebug.d("random teleport");
				return new RandomTeleport(position);
			} else if(powerUp == 7) {
				MyDebug.d("score up");
				return new ScoreUp(position);
			} else if(powerUp == 8) {
				MyDebug.d("shield");
				return new Shield(position);
			} else if(powerUp == 9) {
				MyDebug.d("speedown");
				return new SpeedDown(position);
			} else if(powerUp == 10) {
				MyDebug.d("speedup");
				return new SpeedUp(position);
			} 
			else {
				MyDebug.d("null returned!");
				return null;
			}
		}
	}
}

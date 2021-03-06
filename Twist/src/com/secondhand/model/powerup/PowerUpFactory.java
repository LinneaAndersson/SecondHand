package com.secondhand.model.powerup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.secondhand.model.entity.IPowerUpFactory;
import com.secondhand.model.physics.Vector2;

// Uses the second method described here:
// http://www.electricmonk.nl/log/2009/12/23/weighted-random-distribution/
// to do a Weighted Random Distribution of powerups. 

// a frequency of 10 means the powerups is very common, while 1 means it's very rare.
public final class PowerUpFactory implements IPowerUpFactory {

	private final List<Weight> weights;
	private final int totalWeight;
	private Random rng;

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
		weights.add(new Weight(11, BlackColor.getFrequency()));
		int tw = 0;
		for (final Weight weight : this.weights) {
			if (weight.weight < 1 || weight.weight > 10) {
				throw new AssertionError(
						"powerup frequency must be specified in a scale from 1 to 10(10 is most common, and 1 is least common");
			}
			tw += weight.weight;
		}
		this.totalWeight = tw;
	}

	public void setRandom(final Random rng) {
		this.rng = rng;
	}

	// used for level generation.,
	public PowerUp getRandomPowerUp(final Vector2 position) {

		// do the Weighted Random Distribution
		final int index = rng.nextInt(totalWeight);
		int s = 0;
		Weight result = null;
		for (final Weight weight : this.weights) {
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
			if (powerUp == 1) {
				return new DoubleScore(position);
			} else if (powerUp == 2) {
				return new EatObstacle(position);
			} else if (powerUp == 3) {
				return new ExtraLife(position);
			} else if (powerUp == 4) {
				return new MirroredMovement(position);
			} else if (powerUp == 5) {
				return new RandomPowerUp(position, new Random());
			} else if (powerUp == 6) {
				return new RandomTeleport(position);
			} else if (powerUp == 7) {
				return new ScoreUp(position);
			} else if (powerUp == 8) {
				return new Shield(position);
			} else if (powerUp == 9) {
				return new SpeedDown(position);
			} else if (powerUp == 10) {
				return new SpeedUp(position);
			} else if (powerUp == 11) {
				return new BlackColor(position);
			} else {
				return null;
			}
		}
	}
}

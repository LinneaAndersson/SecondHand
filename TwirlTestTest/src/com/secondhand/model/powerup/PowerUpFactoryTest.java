package com.secondhand.model.powerup;

import java.util.Random;

import junit.framework.TestCase;

import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class PowerUpFactoryTest extends TestCase {

	public void testPowerUpFactory() {

		// we'll do it for the extra life powerup

		class MockRandom extends Random {
			int i = 0;
			private static final long serialVersionUID = 1L;

			public int nextInt(int n) {
				// always double score
				return i += 2;
			}
		}

		PowerUpFactory factory = new PowerUpFactory();
		factory.setRandom(new Random());

		float[] nbrOfRarity = new float[11];
		float[] nbrOfType = new float[11];
		float[] frequency = new float[]{};
		Vector2 pos;
		PowerUp powerup;
		for (int i = 0; i < 1000; i++) {
			pos = new Vector2(i, i);
			powerup = factory.getRandomPowerUp(pos);
			if (powerup != null) {
				assertTrue(pos.almostEquals(powerup.getInitialPosition()));
				switch (powerup.getPowerUpType()) {
				case BLACK_COLOR:
					nbrOfRarity[0] += BlackColor.getFrequency();
					nbrOfType[0] += 1;
					break;
				case DOUBLE_SCORE:
					nbrOfRarity[1] += DoubleScore.getFrequency();
					nbrOfType[1] += 1;
					break;
				case EAT_OBSTACLE:
					nbrOfRarity[2] += EatObstacle.getFrequency();
					nbrOfType[2] += 1;
					break;
				case EXTRA_LIFE:
					nbrOfRarity[3] += ExtraLife.getFrequency();
					nbrOfType[3] += 1;
					break;
				case MIRRORED_MOVEMENT:
					nbrOfRarity[4] += MirroredMovement.getFrequency();
					nbrOfType[4] += 1;
					break;
				case RANDOM_POWER_UP:
					nbrOfRarity[5] += RandomPowerUp.getFrequency();
					nbrOfType[5] += 1;
					break;
				case RANDOM_TELEPORT:
					nbrOfRarity[6] += RandomTeleport.getFrequency();
					nbrOfType[6] += 1;
					break;
				case SCORE_UP:
					nbrOfRarity[7] += ScoreUp.getFrequency();
					nbrOfType[7] += 1;
					break;
				case SHIELD:
					nbrOfRarity[8] += Shield.getFrequency();
					nbrOfType[8] += 1;
					break;
				case SPEED_DOWN:
					nbrOfRarity[9] += SpeedDown.getFrequency();
					nbrOfType[9] += 1;
					break;
				case SPEED_UP:
					nbrOfRarity[10] += SpeedUp.getFrequency();
					nbrOfType[10] += 1;
					break;
				}

			}
		}
		for (int j = 0; j < 11; j++) {
			System.out.println(nbrOfRarity[j]/nbrOfType[j]);
			
		}
	/*	for (int j = 0; j < 11; j++) {
			System.out.print(nbrOfType[j]+"	");
			System.out.print(nbrOfType[j]/totalnbr+ "	");
			System.out.println(nbrOfRarity[j]/nbrOfType[j]);
		}
		System.out.println("Total nbr: " + totalnbr);
		System.out.println("Total rarity: " + totalRarity);
	*/
	}
}

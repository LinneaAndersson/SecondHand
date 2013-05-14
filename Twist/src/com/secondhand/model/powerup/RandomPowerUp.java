package com.secondhand.model.powerup;

import java.util.Random;

import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class RandomPowerUp extends PowerUp {

	private final PowerUp randomPowerUp;

	public RandomPowerUp(final Vector2 position,
			final  IGameWorld gameWorld, Player player) {
		super(position, PowerUpType.RANDOM_POWER_UP, 0, player);

		final Random rng = new Random();
		final int rand = rng.nextInt(PowerUpFactory.NUM_POWER_UPS-1);
		
		
		if(rand == 0) {
			randomPowerUp =  new EatObstacle(position, player);
		} else if(rand == 1) {
			randomPowerUp =  new ExtraLife(position, player);
		}else if(rand == 2) {
			randomPowerUp =  new RandomTeleport(position, gameWorld, player);
		}else if(rand == 3) {
			randomPowerUp =  new ScoreUp(position, player);
		}else if(rand == 4) {
			randomPowerUp =  new Shield(position, player);
		}else if(rand == 5) {
			randomPowerUp =  new SpeedUp(position, player);
		} else if(rand == 6) {
			randomPowerUp =  new DoubleScore(position, player);
		} else if(rand == 7) {
			randomPowerUp =  new MirroredMovement(position, player);
		} else if(rand == 8) {
			randomPowerUp =  new SpeedDown(position, player);
		}else
			randomPowerUp = null;
		
		this.duration = randomPowerUp.getDuration();
	}

	@Override
	public void activateEffect(final Player player) {
		this.randomPowerUp.activateEffect(player);
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		this.randomPowerUp.deactivateEffect(player);
	}

	@Override
	public String getText(){	
		return this.randomPowerUp.getText();
	}
	
	public float getR() {return this.randomPowerUp.getR();}
	public float getG() {return this.randomPowerUp.getG();}
	public float getB() {return this.randomPowerUp.getB();}
}

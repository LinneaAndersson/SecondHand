package com.secondhand.model.powerup;

import java.util.Random;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.model.Vector2;
import com.secondhand.model.resource.PowerUpType;


// TODO: won't work for some fucking reason. Fix it.

public class RandomPowerUp extends PowerUp {

	private final PowerUp randomPowerUp;

	public RandomPowerUp(final Vector2 position,
			final  GameWorld gameWorld) {
		super(position, PowerUpType.RANDOM_POWER_UP, gameWorld, 0);

		final Random rng = new Random();
		final int rand = rng.nextInt(PowerUpFactory.NUM_POWER_UPS-1);
		
		
		if(rand == 0) {
			randomPowerUp =  new EatObstacle(position, gameWorld);
		} else if(rand == 1) {
			randomPowerUp =  new ExtraLife(position, gameWorld);
		}else if(rand == 2) {
			randomPowerUp =  new RandomTeleport(position, gameWorld);
		}else if(rand == 3) {
			randomPowerUp =  new ScoreUp(position, gameWorld);
		}else if(rand == 4) {
			randomPowerUp =  new Shield(position, gameWorld);
		}else if(rand == 5) {
			randomPowerUp =  new SpeedUp(position, gameWorld);
		} else if(rand == 6) {
			randomPowerUp =  new DoubleScore(position, gameWorld);
		} else if(rand == 7) {
			randomPowerUp =  new MirroredMovement(position, gameWorld);
		} else
			randomPowerUp = null;
		
		this.duration = randomPowerUp.getDuration();
	}

	@Override
	public void activateEffect(final Player player) {
		MyDebug.d("applying random powerup up");
		this.randomPowerUp.activateEffect(player);
	}
	
	@Override
	public void deactivateEffect(final Player player) {
		MyDebug.d("unapplying random powerup up");
		
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

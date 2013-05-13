package com.secondhand.model.powerup;

import java.util.List;

import com.secondhand.model.entity.IPowerUp;
import com.secondhand.model.entity.Player;
import com.secondhand.model.entity.RectangleEntity;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public abstract class PowerUp extends RectangleEntity implements IPowerUp {
		
	public final static int WIDTH = 64;
	public final static int HEIGHT = 64;
	
	protected float duration;
	private final PowerUpType powerUpType;
	private final Player player;
	
	public PowerUp (final Vector2 position, final PowerUpType powerUpType, final float duration, Player player) {
		super(position, WIDTH, HEIGHT,true);
		this.duration = duration;
		this.powerUpType = powerUpType;
		this.player = player;
	}
	
	@Override
	public PowerUpType getPowerUpType() {
		return this.powerUpType;
	}
	
	@Override
	public float getDuration() {
		return duration;
	}
	
	@Override
	public abstract void activateEffect(final Player player);
	
	@Override
	public void deactivateEffect(final Player player) { }
	
	@Override
	public float getScoreWorth() {
		throw new IllegalStateException("cannot score points for eating powerup");

	}
	
	@Override
	public boolean hasText(){
		return getText() != null;
	}
	
	@Override
	public String getText(){
		return null;
	}
	
	@Override
	public boolean hasAnother(final Player player) {
		boolean hasAnother = false;
		for (final IPowerUp powerUp : player.getPowerUps()) {
			if (powerUp.getClass() == this.getClass()) // old was DoubleScore.getClass() but this should be right?
				hasAnother = true;
		}
		return hasAnother;	
	}
	
	// how to color the player when the powerup is applied.
	// red, green, blue values, from 0 to 1
	// you can ignore these values for powerups with a duration of 1. 
	// I know that this is technically part of the view, but then we'd have to create a 
	// separate view class for every fucking powerup, and I don't really have the energy to do that. 
	@Override
	public float getR() {return 1f;}
	
	@Override
	public float getG() {return 1f;}
	
	@Override
	public float getB() {return 1f;}
	
	@Override
	public void eaten(){
		super.wasEaten();
	}

	/*public void activatePowerUp() {
		
	}

	public void removePowerUp(PowerUp powerUp) {
		// TODO Auto-generated method stub
		
	}

	public List<PowerUp> getPowerUps() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addPowerUp(){
		
	}*/
	
}

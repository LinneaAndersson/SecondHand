package com.secondhand.model.powerup;

import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.model.RectangleEntity;
import com.secondhand.model.Vector2;
import com.secondhand.model.resource.PowerUpType;

public abstract class PowerUp extends RectangleEntity {
		
	public final static int WIDTH = 64;
	public final static int HEIGHT = 64;
	
	protected float duration;
	private final PowerUpType powerUpType;
	
	public PowerUp (final Vector2 position, final PowerUpType powerUpType, final GameWorld level, final float duration) {
		super(position, WIDTH, HEIGHT,true,  level);
		this.duration = duration;
		this.powerUpType = powerUpType;
	}
	
	public PowerUpType getPowerUpType() {
		return this.powerUpType;
	}
	
	public float getDuration() {
		return duration;
	}
	
	public abstract void activateEffect(final Player player);
	
	public void deactivateEffect(final Player player) { }
	
	@Override
	public float getScoreWorth() {
		throw new IllegalStateException("cannot score points for eating powerup");

	}
	
	public boolean hasText(){
		return getText() != null;
	}
	
	
	public String getText(){
		return null;
	}
	
	public boolean hasAnother(final Player player) {
		boolean hasAnother = false;
		for (final PowerUp powerUp : player.getPowerUps()) {
			if (powerUp.getClass() == this.getClass()) // old was DoubleScore.getClass() but this should be right?
				hasAnother = true;
		}
		return hasAnother;
		
	}
	
}

package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class BlackColor extends PowerUp {
	
	private final static float DURATION = 10;
	
	public BlackColor(final Vector2 position) {
		super(position, PowerUpType.BLACK_COLOR, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
	}
	
	@Override
	public void deactivateEffect(final Player player, final boolean hasAnother) {
	}
	
	public float getR() {return 1f;}
	public float getG() {return 1f;}
	public float getB() {return 1f;}
	
	public static int getFrequency() { return 10; }
}

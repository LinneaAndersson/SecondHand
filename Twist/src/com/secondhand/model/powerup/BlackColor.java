package com.secondhand.model.powerup;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;

public class BlackColor extends PowerUp {
	
	private final static float DURATION = 5;
	
	public BlackColor(final Vector2 position) {
		super(position, PowerUpType.BLACK_COLOR, DURATION);
	}

	@Override
	public void activateEffect(final Player player) {
	}
	
	@Override
	public void deactivateEffect(final Player player, final boolean hasAnother) {
		if(!hasAnother){
			this.resetPlayerColor(player);
		}
	}
	
	public float getR() {return 0f;}
	public float getG() {return 0f;}
	public float getB() {return 0f;}
	
	public static int getFrequency() { return 6; }
}

package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

// I think that if we only want power-UP the
// class doesn't need to be abstract
public abstract class PowerUp extends Entity {
	
	public PowerUp (Vector2 position, float radius) {
		super(position,radius);
	}
	
	public enum Effect{
		SPEED_UP, SCORE_UP, EAT_OBSTACLE, RANDOM_TELEPORT, SHIELD
	}
}

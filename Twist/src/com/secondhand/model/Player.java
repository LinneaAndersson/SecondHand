package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.powerup.PowerUp;

public class Player extends BlackHole {

	private  int score;
	private PowerUp powerUp;
	private String name;

	public Player(final Vector2 position, final float radius, final PhysicsWorld physicsWorld) {
		super(position, radius, physicsWorld, false);
		this.powerUp = null;
		this.score = 0;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore(final int increase) {
		score += increase;
	}
	
	public PowerUp getPowerUp(){
		return powerUp;
	}
	
	public void setPowerUp(final PowerUp powerUp){
		this.powerUp = powerUp;
		if (powerUp != null)
			powerUp.activateEffect(this);
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}

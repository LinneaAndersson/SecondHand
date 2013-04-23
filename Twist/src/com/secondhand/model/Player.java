package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.PowerUp.Effect;

public class Player extends BlackHole {

	private  int score;
	private Effect effect;
	private String name;

	public Player(final Vector2 position, final float radius, final PhysicsWorld physicsWorld) {
		super(position, radius, physicsWorld, false);
		this.effect = Effect.NONE;
		this.score = 0;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore(final int increase) {
		score += increase;
	}
	
	public Effect getEffect(){
		return effect;
	}
	
	public void setEffect(final Effect effect){
		this.effect = effect;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}

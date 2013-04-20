package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.PowerUp.Effect;

public class Player extends BlackHole {

	private  int score;
	private Effect effect;

	public Player(final Vector2 position, final float radius) {
		super(position, radius);
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

}

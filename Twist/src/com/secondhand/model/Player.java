package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.PowerUp.Effect;

public class Player extends BlackHole {

	private int score;
	private Effect effect;

	public Player(Vector2 position, float radius) {
		super(position, radius);
		this.effect = Effect.NONE;
		this.score = 0;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore(int increase) {
		score += increase;
	}
	
	public Effect getEffect(){
		return effect;
	}
	
	public void setEffect(Effect effect){
		this.effect = effect;
	}

}

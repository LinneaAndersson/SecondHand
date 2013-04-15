package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public class Player extends BlackHole{
	private int score=0;
	
	public Player (Vector2 position, int radius) {
		super(position,radius);
		this.score = 0;
	}
	
	public int getScore(){
		return score;  
	}
	
	public void increment(){
		score++;
	}
	
	public void increment(int inc){
		score=score+inc;
	}
	
	public void increaseSize(int inc){
		setRadius(getRadius()+inc);
	}

	
}

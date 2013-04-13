package com.secondhand.twirl;

import com.badlogic.gdx.math.Vector2;

public class Player extends BlackHole{
	private int score=0;
	
	public Player (Vector2 vector, int radius) {
		super(vector,radius);
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

	@Override
	public Entity_enum getEnum() {
		// TODO Auto-generated method stub
		return Entity_enum.PLAYER;
	}
	
}

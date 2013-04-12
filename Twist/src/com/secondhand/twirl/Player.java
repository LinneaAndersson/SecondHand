package com.secondhand.twirl;

public class Player extends BlackHole{
	private int score=0;
	
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

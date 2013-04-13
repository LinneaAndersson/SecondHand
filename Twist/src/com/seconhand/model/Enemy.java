package com.seconhand.model;

import com.badlogic.gdx.math.Vector2;

public class Enemy extends BlackHole {
	private boolean isBigger;
	
	public Enemy (Vector2 vector, int radius) {
		super(vector,radius);
	}
	
	public boolean isBigger(){
		return isBigger;
	}

	@Override
	public Entity_enum getEnum() {
		// TODO Auto-generated method stub
		return null;
	}
}

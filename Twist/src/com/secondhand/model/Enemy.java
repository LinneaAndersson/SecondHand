package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public class Enemy extends BlackHole {
	private boolean isBigger;
	
	public Enemy (Vector2 vector, float radius) {
		super(vector,radius);
	}
	
	public boolean isBigger(){
		return isBigger;
	}

}

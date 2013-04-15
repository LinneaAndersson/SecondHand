package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public abstract class BlackHole extends Entity{
	
	public BlackHole (Vector2 position, float radius) {
		super(position,radius);
	}
	
	public void increaseSize(float increase){
		setRadius(getRadius()+increase);
	}
}

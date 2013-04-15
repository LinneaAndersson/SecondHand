package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	private Vector2 position;
	private int radius;
	
	public Entity (Vector2 position, int radius) {
		this.position = position;
		this.radius = radius;
	}
	
	public void setRadius(int r){
		radius=r;
	}
	
	public void setVector(Vector2 v){
		position = v;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public Vector2 getVector(){
		return position;
	}
	
	
}

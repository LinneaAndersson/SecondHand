package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	private Vector2 position;
	private float radius;
	
	public Entity (Vector2 position, float radius) {
		this.position = position;
		this.radius = radius;
	}
	
	public void setRadius(float r){
		radius=r;
	}
	
	public void setVector(Vector2 v){
		position = v;
	}
	
	public float getRadius(){
		return radius;
	}
	
	public Vector2 getVector(){
		return position;
	}
	
	
}

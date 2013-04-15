package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	private Vector2 position;
	private float radius;
	
	public Entity (Vector2 position, float radius) {
		this.position = position;
		this.radius = radius;
	}
	
	public void setRadius(float radius){
		this.radius=radius;
	}
	
	public void setPosition(Vector2 position){
		this.position = position;
	}
	
	public float getRadius(){
		return this.radius;
	}
	
	public Vector2 getPosition(){
		return this.position;
	}
	
	
}

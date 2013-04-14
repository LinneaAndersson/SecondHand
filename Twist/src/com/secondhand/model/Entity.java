package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	protected enum Entity_enum{
		PLAYER, ENEMY, OBSTACLE, PLANET
	}
	private Vector2 vector;
	private int radius;
	
	public Entity (Vector2 vector, int radius) {
		this.vector = new Vector2(vector);
		this.radius = radius;
	}
	
	public void setRadius(int r){
		radius=r;
	}
	
	public void setVector(){
		
	}
	
	public int getRadius(){
		return radius;
	}
	
	public Vector2 getVector(){
		return vector;
	}
	
	public abstract Entity_enum getEnum();
	
}

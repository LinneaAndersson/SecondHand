package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	protected enum Entity_enum{
		PLAYER, ENEMY, OBSTACLE, PLANET
	}
	private Vector2 position;
	private int radius;
	
	public Entity (Vector2 vector, int radius) {
		this.position = new Vector2(vector);
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
	
	public abstract Entity_enum getEnum();
	
}

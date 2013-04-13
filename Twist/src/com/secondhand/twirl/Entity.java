package com.secondhand.twirl;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	protected enum Entity_enum{
		PLAYER, ENEMY, OBSTACLE, PLANET
	}
	private Position pos;
	private Vector2 vector;
	private int radius;
	
	public Entity (Position pos, Vector2 vector, int radius) {
		this.pos = new Position(pos.getX(),pos.getY());
		this.vector = new Vector2(vector);
		this.radius = radius;
	}
	
	public void setRadius(int r){
		radius=r;
	}
	
	public void setPosition(int x,int y){
		pos.setPosition(x, y);
	}
	
	public void setVector(){
		
	}
	
	public int getRadius(){
		return radius;
	}
	
	public Position getPosition(){
		return pos;
	}
	
	public Vector2 getVector(){
		return vector;
	}
	
	public abstract Entity_enum getEnum();
	
}

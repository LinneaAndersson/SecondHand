package com.secondhand.twirl;

public abstract class Entity {
	private Position pos;
	private Vector vector;
	private int radius;
	
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
	
	public Vector getVector(){
		return vector;
	}
	
}

package com.secondhand.model;

import com.secondhand.view.opengl.Circle;

public abstract class CircleEntity extends Entity {
	
	protected final Circle circle;
	
	public CircleEntity(final Circle circle, final boolean isEdible, final GameWorld level) {
		
		  super(circle,isEdible,level);
		 physics.createType(circle,this);
		
		this.circle = circle;
	}
	
	@Override
	public boolean isCircle(){
		return true;
	}
	
	public void setRadius(final float radius) {
		circle.setRadius(radius);
	}	
	
	@Override
	public float getCenterX() {
		return this.getX();
	}
	
	@Override
	public float getCenterY() {
		return this.getY();
	}
	
	@Override 
	public float getRadius() {
		return circle.getRadius();
	}

	public Circle getCircle() {
		return circle;
	}
}

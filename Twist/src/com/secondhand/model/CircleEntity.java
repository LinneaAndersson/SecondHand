package com.secondhand.model;

import com.secondhand.opengl.Circle;

public abstract class CircleEntity extends Entity {
	
	protected final Circle circle;
	
	public CircleEntity(final Circle circle, final boolean isEdible) {
		super(circle, isEdible);
		this.circle = circle;
	}
	
	public float getRadius() {
		return this.circle.getRadius();
	}
	public void setRadius(final float radius) {
		circle.setRadius(radius);
	}	
	
	@Override 
	public float getArea() {
		return circle.getRadius() * circle.getRadius()  * (float)Math.PI;
	}
}

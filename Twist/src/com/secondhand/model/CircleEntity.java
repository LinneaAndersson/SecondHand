package com.secondhand.model;

import com.secondhand.opengl.Circle;

public abstract class CircleEntity extends Entity {
	
	private final Circle circle;
	
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
	
	public float getCenterX() {
		return this.getX() + this.getRadius();
	}
	
	public float getCenterY() {
		return this.getY() + this.getRadius();
	}
	
	@Override 
	public float getArea() {
		return circle.getRadius() * circle.getRadius()  * (float)Math.PI;
	}

	public Circle getCircle() {
		return circle;
	}
}

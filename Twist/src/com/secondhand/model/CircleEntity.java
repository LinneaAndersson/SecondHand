package com.secondhand.model;


import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;

public abstract class CircleEntity extends Entity {
	
	protected Circle circle;
	
	public CircleEntity(final Vector2 position, final Circle circle, final boolean isEdible) {
		super(position, circle, isEdible);
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

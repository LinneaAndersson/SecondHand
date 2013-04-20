package com.secondhand.model;

import org.anddev.andengine.entity.shape.RectangularShape;

import com.badlogic.gdx.math.Vector2;

public abstract class RectangleEntity extends Entity {
	
	protected RectangularShape rectangle;
	
	public RectangleEntity(Vector2 position, RectangularShape rectangle, boolean isEdible) {
		super(position, rectangle, isEdible);
		this.rectangle = rectangle;
	}
	
	public float getWidth() {
		return this.rectangle.getWidth();
	}
	
	public float getHeight() {
		return this.rectangle.getHeight();
	}
	
	@Override 
	public float getArea() {
		return this.getWidth() * this.getHeight();
	}
}

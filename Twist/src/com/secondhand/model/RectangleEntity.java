package com.secondhand.model;

import org.anddev.andengine.entity.shape.RectangularShape;


public abstract class RectangleEntity extends Entity {
	
	protected RectangularShape rectangle;
	
	public RectangleEntity(final RectangularShape rectangle, final boolean isEdible) {
		super(rectangle, isEdible);
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

	public RectangularShape getRectangle() {
		return this.rectangle;
	}
}

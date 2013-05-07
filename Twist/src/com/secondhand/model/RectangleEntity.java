package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.entity.shape.RectangularShape;


public abstract class RectangleEntity extends Entity {
		

	private final Vector2 position;
	private final float width;
	private final float height;
	
	public PolygonEntity(final Vector2 position,  final List<Vector2> polygon, 
			final boolean isEdible, final GameWorld level) {
		super(isEdible, level);
		
		this.position = position;
		this.polygon = polygon;
	}*/
	
	
	public RectangleEntity(final RectangularShape rectangle, final boolean isEdible, final GameWorld level) {
		super(rectangle, isEdible, level);
		
		this.rectangle = rectangle;
	}
	
	@Override
	public float getRadius() {
		// because don't really use this method for rectangles entities at all.
		return 0;
	}
	
	@Override
	public void createType(){
		//physics.createType(rectangle, this);
	}	
	
	@Override
	public boolean isCircle(){
		return false;
	}
	
	public float getWidth() {
		return this.rectangle.getWidth();
	}
	
	public float getHeight() {
		return this.rectangle.getHeight();
	}
	

	public RectangularShape getRectangle() {
		return this.rectangle;
	}
}

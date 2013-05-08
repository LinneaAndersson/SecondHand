package com.secondhand.model;

import java.util.List;

public abstract class PolygonEntity extends Entity {
		
	//protected final Polygon polygon;
	
	// polygon won't be allowed to grow.
	private float radius; 
	
	private final List<Vector2> polygon;
	
	public PolygonEntity(final Vector2 position,  final List<Vector2> polygon, 
			final boolean isEdible, final GameWorld level) {
		super(position, isEdible, level);
		
		this.polygon = polygon;
	}
	
	@Override
	public boolean isCircle(){
		return false;
	}
	
	
	public List<Vector2> getPolygon() {
		return this.polygon;
	}
	
	public void createType(){
		this.radius = this.physics.computePolygonRadius(getPolygon());
	}
	
	@Override 
	public float getRadius() {
		return radius;
	}
}

package com.secondhand.model;

import com.secondhand.opengl.Polygon;

public abstract class PolygonEntity extends Entity {
	
	protected final Polygon polygon;
	
	public PolygonEntity(final Polygon polygon, final boolean isEdible) {
		super(polygon, isEdible);
		this.polygon = polygon;
	}
	
	@Override 
	public float getArea() {
		return 1;
		// TODO: figure out how to compute area of an arbitrary polygon. 
	}
	
	public Polygon getPolygon() {
		return this.polygon;
	}
}

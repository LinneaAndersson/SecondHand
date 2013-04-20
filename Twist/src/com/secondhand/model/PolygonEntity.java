package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Polygon;

public abstract class PolygonEntity extends Entity {
	
	protected Polygon polygon;
	
	public PolygonEntity(Vector2 position, Polygon polygon, boolean isEdible) {
		super(position, polygon, isEdible);
		this.polygon = polygon;
	}
	
	@Override 
	public float getArea() {
		return 0;
		// TODO: figure out how to compute area of an arbitrary polygon. 
	}
}

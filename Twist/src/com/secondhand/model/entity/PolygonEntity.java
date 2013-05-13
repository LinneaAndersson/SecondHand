package com.secondhand.model.entity;

import java.util.List;

import com.secondhand.model.physics.Vector2;

public abstract class PolygonEntity extends Entity {
		
	private float radius; 
	
	private final List<Vector2> polygon;
	
	public PolygonEntity(final Vector2 position,  final List<Vector2> polygon, 
			final boolean isEdible) {
		super(position, isEdible);
		
		this.polygon = polygon;
	}

	
	
	public List<Vector2> getPolygon() {
		return this.polygon;
	}
	
	public void onPhysicsAssigned(){
		this.radius = this.physics.computePolygonRadius(getPolygon());
	}
	
	@Override 
	public float getRadius() {
		return radius;
	}
}

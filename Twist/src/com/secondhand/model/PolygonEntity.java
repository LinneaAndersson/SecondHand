package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.secondhand.view.opengl.Polygon;

public abstract class PolygonEntity extends Entity {
		
	//protected final Polygon polygon;
	
	// polygon won't be allowed to grow.
	private float radius; 
	
	private final Vector2 position;
	private final List<Vector2> polygon;
	
	public PolygonEntity(final Vector2 position,  final List<Vector2> polygon, 
			final boolean isEdible, final GameWorld level) {
		super(isEdible, level);
		
		this.position = position;
		this.polygon = polygon;
	}
	
	@Override
	public boolean isCircle(){
		return false;
	}
	
	public Vector2 getPosition() {
		return this.position;
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

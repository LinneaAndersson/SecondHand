package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.secondhand.opengl.Polygon;
import com.secondhand.physics.MyPhysicsFactory;

public abstract class PolygonEntity extends Entity {
	
	protected final Polygon polygon;
	
	public PolygonEntity(final Polygon polygon, final boolean isEdible, final PhysicsWorld physicsWorld) {
		super(polygon, isEdible, MyPhysicsFactory.createPolygonBody(physicsWorld,
				polygon, BodyType.DynamicBody, Entity.FIXTURE), true, physicsWorld);
		this.polygon = polygon;
	}
	
	@Override
	public boolean isCircle(){
		return false;
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

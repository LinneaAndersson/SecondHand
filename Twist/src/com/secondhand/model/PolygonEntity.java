package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.opengl.Polygon;
import com.secondhand.physics.MyPhysicsFactory;

public abstract class PolygonEntity extends Entity {
	
	protected final Polygon polygon;
	
	public PolygonEntity(final Polygon polygon, final boolean isEdible, final PhysicsWorld physicsWorld,
			final FixtureDef fixtureDef) {
		super(polygon, isEdible, MyPhysicsFactory.createPolygonBody(physicsWorld,
				polygon, BodyType.DynamicBody, fixtureDef), true, physicsWorld);
		this.polygon = polygon;
		polygon.setBody(this.getBody());
	}
	
	@Override
	public boolean isCircle(){
		return false;
	}
	
	@Override 
	public float getArea() {
		return 1; 
	}
	
	public Polygon getPolygon() {
		return this.polygon;
	}
}

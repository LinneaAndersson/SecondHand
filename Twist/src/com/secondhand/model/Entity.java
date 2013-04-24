package com.secondhand.model;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class Entity {

	private final Body body;
	private final IShape shape;
	private final boolean isEdible;
	private String assetName;
	private final PhysicsWorld physicsWorld;
	
	protected final boolean updateRotation;
	
	// TODO: refactor out into a constructor paramater maybe?
	public final static FixtureDef FIXTURE = PhysicsFactory.createFixtureDef(1, 0.5f,
			0.5f);
	
	public Entity(final Shape shape, final boolean isEdible, final Body body,  final boolean updateRotation,
			final PhysicsWorld physicsWorld) {
		this.body = body;
		this.shape = shape;
		this.isEdible = isEdible;
		this.updateRotation = updateRotation;
		this.physicsWorld = physicsWorld;
		
		registerBody(body); //NOPMD
	}
	
	protected final void registerBody(final Body body) {

		// we need this when doing collisions handling between entities and
		// black holes:
		body.setUserData(this);
		physicsWorld.registerPhysicsConnector(new PhysicsConnector(this.shape, this.body, true, updateRotation));
	}
	
	public float getX() {
		return shape.getX();
	}
	
	public float getY() {
		return shape.getY();
	}

	public Body getBody() {
		return body;
	}

	public IShape getShape() {
		return shape;
	}
	
	public boolean isEdible() {
		return this.isEdible;
	}
	
	public String getImageName(){
		return assetName;
	}
	
	// TODO: we should probably cache the result of this computation, because it can 
	// get quite expensive, especially for polygons. 
	public abstract float getArea();

	public float getCenterX() {
		return getX();
	}

	public float getCenterY() {
		return getY();
	}
}


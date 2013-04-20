package com.secondhand.model;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {

	private Body body;
	private final IShape shape;
	private boolean isEdible;
	
	public Entity(Vector2 position, Shape shape, boolean isEdible) {
		this.shape = shape;
		this.isEdible = isEdible;
	}
	
	
	
	public float getX() {
		return shape.getX();
	}
	
	public float getY() {
		return shape.getY();
	}

	public void setBody(Body body){
		this.body = body;
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
	
	// TODO: we should probably cache the result of this computation, because it can 
	// get quite expensive, especially for polygons. 
	public abstract float getArea();
}

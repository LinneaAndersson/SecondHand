package com.secondhand.model;

import org.anddev.andengine.entity.shape.IShape;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {

	private float radius;
	private Body body;
	private final IShape shape;
	private boolean isEdible;
	
	public Entity(Vector2 position, float radius, IShape shape, boolean isEdible) {
		this.radius = radius;
		this.shape = shape;
		this.isEdible = isEdible;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}	
	
	public void setBody(Body body){
		this.body = body;
	}
	
	public float getRadius() {
		return this.radius;
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
	
	

}

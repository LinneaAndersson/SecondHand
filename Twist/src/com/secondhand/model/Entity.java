package com.secondhand.model;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {

	private Body body;
	private final IShape shape;
	private final boolean isEdible;
	private String assetName;
	
	public Entity(final Shape shape, final boolean isEdible) {
		this.shape = shape;
		this.isEdible = isEdible;
	}
	
	public float getX() {
		return shape.getX();
	}
	
	public float getY() {
		return shape.getY();
	}

	public void setBody(final Body body){
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
	
	public String getImageName(){
		return assetName;
	}
	
	public void setImageName(String assetName){
		this.assetName=assetName;
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


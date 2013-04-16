package com.secondhand.model;

import org.anddev.andengine.entity.shape.IShape;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.opengl.Circle;

public abstract class Entity {

	private Vector2 position;
	private float radius;
	private Body body;
	private IShape shape;

	public Entity(Vector2 position, float radius) {
		this.position = position;
		this.radius = radius;
		shape = new Circle(position.x, position.y, radius);
	}
	
	public Entity(Vector2 position, float radius, IShape shape) {
		this.position = position;
		this.radius = radius;
		this.shape = shape;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public void setBody(Body body){
		this.body = body;
	}
	
	public void setShape(IShape shape){
		this.shape = shape;
	}

	public float getRadius() {
		return this.radius;
	}

	public Vector2 getPosition() {
		return this.position;
	}

	public Body getBody() {
		return body;
	}

	public IShape getShape() {
		return shape;
	}

}

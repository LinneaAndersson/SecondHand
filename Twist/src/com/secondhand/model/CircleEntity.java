package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.secondhand.opengl.Circle;

public abstract class CircleEntity extends Entity {
	
	private final Circle circle;
	
	public CircleEntity(final Circle circle, final boolean isEdible, final PhysicsWorld physicsWorld,
			final boolean updateRotation) {
		
		super(circle, isEdible, PhysicsFactory.createCircleBody(physicsWorld,
				circle, BodyType.DynamicBody, Entity.FIXTURE), updateRotation, physicsWorld);
		
		this.circle = circle;
	}
	
	public float getRadius() {
		return this.circle.getRadius();
	}
	
	public void setRadius(final float radius) {
		circle.setRadius(radius);
	}	
	
	@Override
	public float getCenterX() {
		return this.getX() + this.getRadius();
	}
	
	@Override
	public float getCenterY() {
		return this.getY() + this.getRadius();
	}
	
	@Override 
	public float getArea() {
		return circle.getRadius() * circle.getRadius()  * (float)Math.PI;
	}

	public Circle getCircle() {
		return circle;
	}
}

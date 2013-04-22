package com.secondhand.model;

import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


public abstract class RectangleEntity extends Entity {
	
	protected RectangularShape rectangle;
	
	public RectangleEntity(final RectangularShape rectangle, final boolean isEdible, final PhysicsWorld physicsWorld,
			final boolean updateRotation) {
		super(rectangle, isEdible, PhysicsFactory.createCircleBody(physicsWorld,
				rectangle, BodyType.DynamicBody, Entity.FIXTURE), updateRotation, physicsWorld);
		this.rectangle = rectangle;
	}
	
	public float getWidth() {
		return this.rectangle.getWidth();
	}
	
	public float getHeight() {
		return this.rectangle.getHeight();
	}
	
	@Override 
	public float getArea() {
		return this.getWidth() * this.getHeight();
	}

	public RectangularShape getRectangle() {
		return this.rectangle;
	}
}

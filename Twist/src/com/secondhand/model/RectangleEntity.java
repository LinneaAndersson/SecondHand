package com.secondhand.model;

import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;


public abstract class RectangleEntity extends Entity {
	
	protected RectangularShape rectangle;
	
	public RectangleEntity(final RectangularShape rectangle, final boolean isEdible, final GameWorld level, final FixtureDef fixtureDef) {
		super(rectangle, isEdible, PhysicsFactory.createCircleBody(level.getPhysicsWorld(),
				rectangle, BodyType.DynamicBody, fixtureDef), level);
		this.rectangle = rectangle;
	}
	
	@Override
	public float getRadius() {
		// because don't really use this method for rectangles entities at all.
		return 0;
	}
	
	@Override
	public boolean isCircle(){
		return false;
	}
	
	public float getWidth() {
		return this.rectangle.getWidth();
	}
	
	public float getHeight() {
		return this.rectangle.getHeight();
	}
	

	public RectangularShape getRectangle() {
		return this.rectangle;
	}
}

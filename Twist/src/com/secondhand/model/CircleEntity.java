package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;	
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.opengl.Circle;

public abstract class CircleEntity extends Entity {
	
	protected final Circle circle;
	protected final PhysicsWorld physicsWorld;
	
	
	//public static Body createCircleBody(final PhysicsWorld pPhysicsWorld, final float pCenterX, final float pCenterY, final float pRadius, final float pRotation, final BodyType pBodyType, final FixtureDef pFixtureDef) {

	
	protected static Body createNewCircleBody(final Circle circle,  final PhysicsWorld physicsWorld, final FixtureDef fixtureDef) {
		return PhysicsFactory.createCircleBody(physicsWorld,
				circle.getX(), circle.getY(), circle.getRadius(),circle.getRotation(), BodyType.DynamicBody, fixtureDef);
	}
	
	public CircleEntity(final Circle circle, final boolean isEdible, final Level level,
			final boolean updateRotation, final FixtureDef fixtureDef) {
		
		super(circle, isEdible, createNewCircleBody(circle, level.getPhysicsWorld(), fixtureDef), updateRotation, level);
		
		this.physicsWorld = level.getPhysicsWorld();
		
		this.circle = circle;
	}
	
	@Override
	public boolean isCircle(){
		return true;
	}
	
	public void setRadius(final float radius) {
		circle.setRadius(radius);
	}	
	
	@Override
	public float getCenterX() {
		return this.getX();
	}
	
	@Override
	public float getCenterY() {
		return this.getY();
	}
	
	@Override 
	public float getRadius() {
		return circle.getRadius();
	}

	public Circle getCircle() {
		return circle;
	}
}

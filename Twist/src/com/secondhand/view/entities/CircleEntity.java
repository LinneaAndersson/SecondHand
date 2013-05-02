package com.secondhand.view.entities;

import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import com.secondhand.view.opengl.Circle;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.model.Entity;
import com.secondhand.model.GameWorld;

public abstract class CircleEntity extends Entity {
	
	protected final Circle circle;
	
	//public static Body createCircleBody(final PhysicsWorld pPhysicsWorld, final float pCenterX, final float pCenterY, final float pRadius, final float pRotation, final BodyType pBodyType, final FixtureDef pFixtureDef) {

	
	protected static Body createNewCircleBody(final Circle circle,  final PhysicsWorld physicsWorld, final FixtureDef fixtureDef) {
		return PhysicsFactory.createCircleBody(physicsWorld,
				circle.getX(), circle.getY(), circle.getRadius(),circle.getRotation(), BodyType.DynamicBody, fixtureDef);
	}
	
	public CircleEntity(final Circle circle, final boolean isEdible, final GameWorld level,final FixtureDef fixtureDef) {
		
		super(circle, isEdible, createNewCircleBody(circle, level.getPhysicsWorld(), fixtureDef), level);
		
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

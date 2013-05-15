package com.secondhand.model.entity;

import com.secondhand.model.physics.Vector2;

public abstract class CircleEntity extends Entity {	
	protected float radius;
	
	public CircleEntity(final Vector2 position, final float radius, final boolean isEdible) {  
		super(position, isEdible);
		
		setRadius(radius);
	}
	
	
	@Override
	public void onPhysicsAssigned(){
	}
	
	@Override 
	public float getRadius() {
		return this.radius;
	}
	
	public void setRadius(final float radius){

		if(radius < 0 ) throw new AssertionError();
		this.radius = radius;
	}
}

package com.secondhand.model.entity;

import com.secondhand.model.physics.Vector2;

public abstract class CircleEntity extends Entity {	
	protected float radius;
	
	public CircleEntity(final Vector2 position, final float radius, final boolean isEdible) {  
		super(position, isEdible);
		
		this.radius = radius;
	}
	
	@Override
	public void onPhysicsAssigned(){
	}
	
	@Override 
	public float getRadius() {
		return this.radius;
	}
}

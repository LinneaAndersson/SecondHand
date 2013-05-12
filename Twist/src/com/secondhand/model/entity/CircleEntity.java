package com.secondhand.model.entity;

import com.secondhand.model.physics.Vector2;

public abstract class CircleEntity extends Entity {	
	protected final float radius;
	
	public CircleEntity(final Vector2 position, final float radius, final boolean isEdible, final GameWorld level) {  
		super(position, isEdible,level);
		this.radius = radius;
	}
	
	
	@Override
	public void onPhysicsAssigned(){
	}
	
	@Override 
	public float getRadius() {
		if(this.physics != null)
			return this.physics.getRadius();
		else
			return this.radius;
	}
}

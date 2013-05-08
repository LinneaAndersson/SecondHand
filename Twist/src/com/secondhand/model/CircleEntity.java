package com.secondhand.model;

public abstract class CircleEntity extends Entity {	
	protected final float radius;
	
	public CircleEntity(final Vector2 position, final float radius, final boolean isEdible, final GameWorld level) {  
		super(position, isEdible,level);
		this.radius = radius;
	}
	
	@Override
	public boolean isCircle(){
		return true;
	}
	
	@Override
	public void createType(){
	}
	
	
	@Override 
	public float getRadius() {
		if(this.physics != null)
			// return for body
			return this.physics.getRadius();
		else
			return this.radius;
	}
}

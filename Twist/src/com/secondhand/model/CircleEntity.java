package com.secondhand.model;

import com.secondhand.debug.MyDebug;
import com.secondhand.view.opengl.Circle;

public abstract class CircleEntity extends Entity {
	
	protected final Circle circle;
	
	public CircleEntity(final Circle circle, final boolean isEdible, final GameWorld level) {
		
		  super(circle,isEdible,level);
		  // the body should be created in the view instead. 
	
		  //physics.createType(circle,this);
		
		this.circle = circle;

	}
	
	@Override
	public boolean isCircle(){
		return true;
	}
	
	@Override
	public void createType(){
		physics.createType(circle,this); 
	}
	
	public void setRadius(final float radius) {
		circle.setRadius(radius);
	}	
	
	@Override 
	public float getRadius() {
		return circle.getRadius();
	}

	public Circle getCircle() {
		return circle;
	}
}

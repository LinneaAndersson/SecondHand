package com.secondhand.model;

import com.secondhand.debug.MyDebug;
import com.secondhand.view.opengl.Circle;

public abstract class CircleEntity extends Entity {
	
	protected final Circle circle;
	
	public CircleEntity(final Circle circle, final boolean isEdible, final GameWorld level) {
		
		  super(circle,isEdible,level);
		  MyDebug.d("Now we create Circle");
		  // the body should be created in the view instead. 
		  
		  
		
		this.circle = circle;
		MyDebug.d("Nw we create Circle");
	}
	
	@Override
	public boolean isCircle(){
		return true;
	}
	
	@Override
	public void createType(){
		//physics.createType(circle,this);
		MyDebug.d("creating playerType" + physics);
		physics.createType(circle,this); 
		  MyDebug.d("Now we have created playerType");
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

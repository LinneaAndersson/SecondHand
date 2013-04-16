package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;

public abstract class BlackHole extends Entity{
	
	public BlackHole (Vector2 position, float radius) {
		super(position,radius, new Circle(position.x, position.y, radius));
	}
	
	public void increaseSize(float increase){
		setRadius(getRadius()+increase);
	}
	
	/**
	 * If sizes are equal then false is returned.
	 */
	// if the other entity is a obstacle then should false be returned?
	public boolean isBiggerThan(Entity entity){
		return this.getRadius() > entity.getRadius();
	}
}

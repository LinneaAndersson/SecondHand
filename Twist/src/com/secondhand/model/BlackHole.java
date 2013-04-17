package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;

public abstract class BlackHole extends Entity{
	
	public BlackHole (Vector2 position, float radius) {
		// TODO load texture instead of creating Circle
		super(position,radius, new Circle(position.x, position.y, radius), true);
	}
	
	public void increaseSize(float increase){
		setRadius(getRadius()+increase);
	}
	
	/**
	 * If sizes are equal then false is returned.
	 */
	public boolean isBiggerThan(Entity entity){
		if(!entity.isEdible())
			return false;
		return this.getRadius() > entity.getRadius();
	}
}

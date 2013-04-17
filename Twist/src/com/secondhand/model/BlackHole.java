package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;

public abstract class BlackHole extends Entity{
	
	Circle circle;
	
	public BlackHole (Vector2 position, float radius) {
		// TODO load texture instead of creating Circle
		super(position,radius, new Circle(position.x, position.y, radius), true);
		circle = (Circle) this.getShape();
	}
	
	public void increaseSize(float increase){
		setRadius(getRadius()+increase);
	}
	
	@Override
	public void setRadius(float radius) {
		super.setRadius(radius);
		circle.setRadius(radius);
	}	
	
	/**
	 * If sizes are equal then false is returned.
	 */
	public boolean canEat(Entity entity){
		if(!entity.isEdible())
			return false;
		return this.getRadius() > entity.getRadius();
	}
}

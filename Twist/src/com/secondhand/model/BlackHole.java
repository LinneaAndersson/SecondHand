package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;

public abstract class BlackHole extends CircleEntity{
	
	public BlackHole (final Vector2 position, final float radius, final PhysicsWorld physicsWorld, final boolean updateRotation) {
		// TODO load texture instead of creating Circle
		super(new Circle(position.x, position.y, radius), true, physicsWorld, updateRotation);
	}
	
	public void increaseSize(final float increase){
		setRadius(getRadius()+increase);
	}
	
	/**
	 * If sizes are equal then false is returned.
	 */
	public boolean canEat(final Entity entity){
		if(!entity.isEdible()) {
			return false;
		}
		return this.getArea() > entity.getArea();
	}
}

package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.secondhand.debug.MyDebug;
import com.secondhand.opengl.Circle;

public abstract class BlackHole extends CircleEntity{
	
	public BlackHole (final Vector2 position, final float radius, final PhysicsWorld physicsWorld, final boolean updateRotation) {
		// TODO load texture instead of creating Circle
		super(new Circle(position.x, position.y, radius), true, physicsWorld, updateRotation);
	}
	
	private void increaseSize(final float increase){
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


	public void eatEntity(final Entity entity) {

		// Detach the shape from AndEngine-rendering
		entity.getShape().detachSelf();
		

		// remove the eaten entity from the physics world:
		// TODO: we should have a better way of accessing the destroyer
		Universe.getInstance().getPhysicsDestroyer().destroy(entity.getShape());

		// TODO: we should use general entities instead, but for debugging purposes we'll do this cast.
		// we'll fix it later.
		final Planet planet = (Planet)entity;
		
		// increase the size of the rendered circle.
		this.increaseSize(planet.getRadius());
		
		
		// now the must also increase the size of the physics body.
		
		// see this for setting the correct radius:
		// http://www.emanueleferonato.com/2009/12/12/scaling-objects-with-box2d/
		

	}
}

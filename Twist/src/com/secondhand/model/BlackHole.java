package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
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
		Universe.getInstance().getPhysicsDestroyer().destroy(entity.getShape(), true);

		// TODO: we should use general entities instead, but for debugging purposes we'll do this cast.
		// we'll fix it later.
		final Planet planet = (Planet)entity;
		
		
		// increase the size of the rendered circle.
		this.increaseSize(planet.getRadius());
		
		
		// now the must also increase the size of the circle physics body
		
		final CircleShape shape = (CircleShape)getBody().getFixtureList().get(0).getShape();
		
		

		shape.setRadius(getRadius() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		Vector2 currentPosition = shape.getPosition();
		float inc = planet.getRadius() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
		Vector2 newPosition = currentPosition.add(inc, inc);
		
		shape.setPosition(newPosition);
			
		
		/*Universe.getInstance().getPhysicsDestroyer().destroy(this.getShape(), true);

		final Body newBody = super.createNewCircleBody(this.circle, this.physicsWorld);
		
		// TODO: this won't remove the update handler. Fix that as well.
		super.registerBody(newBody);*/
		
	}
}

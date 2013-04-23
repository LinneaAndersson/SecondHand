package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.secondhand.opengl.Circle;
import com.secondhand.physics.PhysicsDestroyer;

public abstract class BlackHole extends CircleEntity {

	public BlackHole(final Vector2 position, final float radius,
			final PhysicsWorld physicsWorld, final boolean updateRotation) {
		// TODO load texture instead of creating Circle
		super(new Circle(position.x, position.y, radius), true, physicsWorld,
				updateRotation);
	}

	private void increaseSize(final float increase) {
		setRadius(getRadius() + increase);
	}

	/**
	 * If sizes are equal then false is returned.
	 */
	public boolean canEat(final Entity entity) {
		if (!entity.isEdible()) {
			return false;
		}
		return this.getArea() > entity.getArea();
	}

	public void eatEntity(final Entity entity) {

		// Detach the shape from AndEngine-rendering
		entity.getShape().detachSelf();

		if (this instanceof Player) {
			if (entity instanceof Planet)
				Universe.getInstance().getLevel().getPlayer().increaseScore(10);
			else
				Universe.getInstance().getLevel().getPlayer().increaseScore(20);
		}

		// remove the eaten entity from the physics world:
		// TODO: we should have a better way of accessing the destroyer

		// can't we make physicsDestroyer singleton? and get it like universe
		PhysicsDestroyer.getInstance()
				.destroy(entity.getShape(), true);

		// TODO: we should use general entities instead, but for debugging
		// purposes we'll do this cast.
		// we'll fix it later.
		final Planet planet = (Planet) entity;

		// increase the size of the rendered circle.
		this.increaseSize(planet.getRadius());

		// now the must also increase the size of the circle physics body

		final CircleShape shape = (CircleShape) getBody().getFixtureList()
				.get(0).getShape();

		shape.setRadius(getRadius()
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);	

		final Vector2 currentPosition = shape.getPosition();
		final float inc = (planet.getRadius())
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
		final Vector2 newPosition = currentPosition.add(inc, inc);

		shape.setPosition(newPosition);

	//	this.getBody().setTransform(newPosition, 0);
		
		/*
		 * Universe.getInstance().getPhysicsDestroyer().destroy(this.getShape(),
		 * true);
		 * 
		 * final Body newBody = super.createNewCircleBody(this.circle,
		 * this.physicsWorld);
		 * 
		 * // TODO: this won't remove the update handler. Fix that as well.
		 * super.registerBody(newBody);
		 */

	}
}

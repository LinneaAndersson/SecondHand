package com.secondhand.model.entity;

import com.secondhand.model.physics.Vector2;

public abstract class CircleEntity extends Entity {
	protected float radius;

	public CircleEntity(final Vector2 position, final float radius,
			final boolean isEdible) {
		super(position, isEdible);

		this.radius = radius;
	}

	@Override
	public float getRadius() {
		return this.radius;
	}

	/* Calculates suface position relative to a given position. */
	public Vector2 calculateSurfacePosition(final Vector2 relativePosition,
			final boolean farSide) {
		final Vector2 entityCenterPosition = new Vector2(getCenterX(),
				getCenterY());
  
		/* Factor is negative if surfacePosition is far side of circle */
		final float factor = (farSide ? -1 : 1) * getRadius()
				/ relativePosition.dst(entityCenterPosition);

		/* Approximate the surface x and y values.
		 * Entity center position plus the fraction of the distance in x and y respectively,
		 * based on previous calculated factor. */
		final float surfaceX = entityCenterPosition.x + factor
				* (relativePosition.x - entityCenterPosition.x);
		final float surfaceY = entityCenterPosition.y + factor
				* (relativePosition.y - entityCenterPosition.y);

		return new Vector2(surfaceX, surfaceY);
	}
}

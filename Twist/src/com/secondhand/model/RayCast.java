package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

public class RayCast implements RayCastCallback {
	private Entity entity;
	private Enemy enemy;
	private boolean straightLine;

	public RayCast(Enemy enemy, Entity entity) {
		this.entity = entity;
		this.enemy = enemy;
		straightLine = true;
	}

	@Override
	public float reportRayFixture(final Fixture fixture, final Vector2 point,
			final Vector2 normal, final float fraction) {

		if (((Entity) fixture.getBody().getUserData()) == entity) {
			return 1;

		} else if (enemy.canEat((Entity) fixture.getBody().getUserData())) {
			return 1;
		}

		straightLine = false;

		return 0;
	}

	public boolean isStraightLine() {
		return straightLine;
	}
}

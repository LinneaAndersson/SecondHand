package com.secondhand.view.physics;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

// PASS ANDENGINE COORDINATES TO THIS CLASS!!!
public final class PhysicsAreaChecker {

	private PhysicsAreaChecker() {
	}

	private static boolean occupied = false;

	public static boolean isRectangleAreaUnoccupied(
			final Vector2 bottomLeftCorner, final Vector2 topRightCorner,
			final PhysicsWorld physicsWorld) {

		occupied = false;

		physicsWorld.QueryAABB(new Callback(), bottomLeftCorner.x
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
				bottomLeftCorner.y
						/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
				topRightCorner.x
						/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
				topRightCorner.y
						/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);

		return !occupied;
	}

	private static class Callback implements QueryCallback {

		@Override
		public boolean reportFixture(final Fixture fixture) {

			occupied = true;

			// stop querying for fixtures, we already know that the area is
			// unoccupied.
			return true;
		}

	}
}

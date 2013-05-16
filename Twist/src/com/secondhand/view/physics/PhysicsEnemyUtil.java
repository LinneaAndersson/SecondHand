package com.secondhand.view.physics;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.secondhand.model.physics.IPhysicsObject;

// contains the two raycast classes that enemy uses.
public class PhysicsEnemyUtil {
	private boolean straightLine;
	private final PhysicsWorld physics;

	public PhysicsEnemyUtil(final PhysicsWorld physics) {
		straightLine = true;
		this.physics = physics;
	}

	//true if there is a line where there are no uneatable entities
	public boolean straightLine(final IPhysicsObject entity, final IPhysicsObject enemy) {
		straightLine = true;
		physics.rayCast(new RayCastCallback() {

			@Override
			public float reportRayFixture(final Fixture fixture,
					final Vector2 point, final Vector2 normal,
					final float fraction) {
				final IPhysicsObject tmp = ((IPhysicsObject) fixture.getBody().getUserData());
				if (tmp.equals(entity)) {
					return 1;

				} else if (enemy.getRadius() > tmp.getRadius() && tmp.isEdible()) {
					return 1;
				}

				straightLine = false;

				return 0;
			}
		}, new Vector2(enemy.getCenterX(),enemy.getCenterY()) , new Vector2(entity.getCenterX(), entity.getCenterY()));

		return straightLine;
	}

}
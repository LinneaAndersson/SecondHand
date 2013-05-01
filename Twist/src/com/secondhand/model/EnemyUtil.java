package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

public class EnemyUtil {
	private Enemy enemy;
	private static boolean straightLine;
	private PhysicsWorld physics;

	public EnemyUtil(Enemy enemy, PhysicsWorld physics) {
		this.enemy = enemy;
		straightLine = true;
		this.physics = physics;
	}

	public boolean straightLine(final Entity entity) {
		straightLine = true;
		physics.rayCast(new RayCastCallback() {

			@Override
			public float reportRayFixture(final Fixture fixture,
					final Vector2 point, final Vector2 normal,
					final float fraction) {

				if (((Entity) fixture.getBody().getUserData()) == entity) {
					return 1;

				} else if (enemy.canEat((Entity) fixture.getBody()
						.getUserData())) {
					return 1;
				}

				straightLine = false;

				return 0;
			}
		}, enemy.getBody().getWorldCenter(), entity.getBody().getWorldCenter());

		return straightLine;
	}

	public Vector2 dangerClose() {
		Vector2 v =  enemy.getBody().getWorldCenter();
		Vector2 v2 = enemy.getBody().getLinearVelocity();
		physics.rayCast(new RayCastCallback(){

			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point,
					Vector2 normal, float fraction) {
				
				return 0;
			}
			
		},v, null);
		return null;

	}
}

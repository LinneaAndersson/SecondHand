package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.secondhand.model.powerup.PowerUp;

public class Enemy extends BlackHole {

	private static boolean straightLine = true;
	private static Entity danger = null;
	// because someone changed getArea to getRadius I
	// had to do this.
	private float area;

	public Enemy(final Vector2 vector, final float radius,
			final GameWorld level, final float maxSpeed) {
		super(vector, radius, level, true, maxSpeed);

		area = getRadius() * getRadius() * (float) Math.PI;
	}

	// player has highest chase-priority
	public void moveEnemy(final Entity player, final List<Entity> entityList) {
		if (isCloseToEntity(player) && canEat(player)) {
			dangerCheck(player);
		} else {
			dangerCheck(getHighesPriority(entityList));
		}

	}

	// may be needed depending on search area size
	// true if road to entity is clear. also true
	// if blocking entity is edible

	private boolean straightToEntity(final Entity entity) {
		straightLine = true;

		physicsWorld.rayCast(new RayCastCallback() {

			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point,
					Vector2 normal, float fraction) {

				if (((Entity) fixture.getBody().getUserData()) == entity) {
					return 1;

				} else if (canEat((Entity) fixture.getBody().getUserData())) {
					return 1;
				}

				straightLine = false;

				return 0;

			}
		}, this.getBody().getWorldCenter(), entity.getBody().getWorldCenter());

		return straightLine;
	}

	// checks if enemy is close enough to start chasing
	// the entity
	private boolean isCloseToEntity(final Entity entity) {

		final float dx = entity.getCenterX() - this.getCenterX();

		final float dy = entity.getCenterY() - this.getCenterY();

		// the hunting area is tmp like this, don't know
		// why i choose area*100
		return dx * dx + dy * dy <= area * 100;

	}

	// chase after smallest entity first
	// could create many get-something and make different
	// enemies behave different ( getClosest... )
	// could implement worldquery instead of sending the complete
	// entityList. if i did they hunting area would be rectangular instead
	// would be nice to not return null
	private Entity getHighesPriority(final List<Entity> entityList) {
		Entity entity = null;
		for (final Entity e : entityList) {
			if (isCloseToEntity(e) && canEat(e)) {
				entity = getSmaller(entity, e);
			}
		}
		return entity;
	}

	private Entity getSmaller(final Entity entity, final Entity e) {
		if (entity == null || e.getRadius() < entity.getRadius()) {
			return e;
		} else {
			return entity;
		}
	}

	private void dangerCheck(Entity entity) {
		// TODO change the null-check to something nicer

		if (entity != null) {
			if (straightToEntity(entity)) {
			//	MyDebug.d("Enemy: applyMovement towards " + entity.getClass());
				applyMovement(new Vector2(
						(entity.getCenterX() - this.getCenterX()),
						entity.getCenterY() - this.getCenterY()));

			} else {
				//MyDebug.d("Enemy: stopMovement");
				stopMovement();
				retreat(isCloseToDanger());

			}
		}
	}
	
	private boolean isCloseToDanger() {

		physicsWorld.QueryAABB(new QueryCallback() {

			@Override
			public boolean reportFixture(Fixture fixture) {
				danger = ((Entity) fixture.getBody().getUserData());

				return false;
			}
		}, 0, 0, 0, 0);

		return danger != null;
	}

	private void stopMovement() {
		this.getBody().setLinearVelocity(new Vector2());
		this.getBody().setAngularVelocity(0);

	}

	private void retreat(boolean inDanger) {
		if (inDanger) {
			applyMovement(new Vector2(this.getCenterX() - danger.getCenterX(),
					this.getCenterY() - danger.getCenterY()));
			danger = null;
		}

	}

	// responsible for moving the enemies
	// at first we only have them moving straight at the player,
	// later we can add more functionality
	// TODO avoid larger stuff, chase smaller stuff
	// move in a smart way(no suicide)
	private void applyMovement(Vector2 movementVector) { // NOPMD

		// the vector from enemy to the player

		// need to slow them down, they are to dam fast
		// otherwise
		// if(body is moving) we need mul(0.001)
		// for better mobility(so they won't just
		// go forward)
		// lower maxSpeed than player.

		if (movementVector.len() > 0) {
			// we want to apply larger force when enemy is
			// turning (changing direction). so we need a better
			// test than above
			movementVector = movementVector.mul(0.0001f);
		} else {
			movementVector = movementVector.mul(0.00001f);
		}

		this.move(movementVector);

	}

	protected void handlePowerUp(final PowerUp powerUp) {
		// enemies can't eat powerups :(
	}

	protected void wasEaten() {
		this.level.getEntityManager().removeEnemyFromList(this);
		super.wasEaten();
	}
}

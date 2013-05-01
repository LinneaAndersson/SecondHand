package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.powerup.PowerUp;

public class Enemy extends BlackHole {

	private static final float ENEMY_MAX_SPEED = 10;

	private EnemyUtil util;
	// because someone changed getArea to getRadius I
	// had to do this.
	private float huntingArea;

	public Enemy(final Vector2 vector, final float radius, final GameWorld level) {
		super(vector, radius, level, ENEMY_MAX_SPEED);
		huntingArea = getHuntingArea();
		util = new EnemyUtil(this, physicsWorld);
		

		// makes the enemy move much smother
		getBody().setLinearDamping(1);

		/*
		 * FixtureDef f = new FixtureDef(); f.isSensor = true; Shape s = new
		 * CircleShape(); s.setRadius((getRadius()+5)/32);
		 * this.getBody().createFixture(f);
		 */
	}

	public float getHuntingArea() {
		return getRadius() * getRadius() * (float) Math.PI * 50;
	}

	// player has highest chase-priority
	public void moveEnemy(final Entity player, final List<Entity> entityList) {
		if (isCloseToEntity(player) && canEat(player)) {
			dangerCheck(player);
		} else {
			dangerCheck(getHighesPriority(entityList));
		}

	}

	// checks if enemy is close enough to start chasing
	// the entity
	private boolean isCloseToEntity(final Entity entity) {

		final float dx = entity.getCenterX() - this.getCenterX();

		final float dy = entity.getCenterY() - this.getCenterY();

		return dx * dx + dy * dy <= huntingArea;

	}

	// chase after smallest entity first
	// could create many get-something and make different
	// enemies behave different ( getClosest... )
	private Entity getHighesPriority(final List<Entity> entityList) {
		Entity entity = null;
		for (final Entity e : entityList) {

			if (e.getClass() != PowerUp.class && isCloseToEntity(e)
					&& canEat(e)) {
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

	private void dangerCheck(final Entity entity) {
		// TODO change the null-check to something nicer
		if (entity != null) {

			if (util.straightLine(entity)) {
				// MyDebug.d("Enemy: applyMovement towards " +
				// entity.getClass());
				// closeToDanger();
				applyMovement(new Vector2(
						(entity.getCenterX() - this.getCenterX()),
						entity.getCenterY() - this.getCenterY()));

			} else {
				stopMovement();

			}
			if (huntingArea != getHuntingArea()) {
				huntingArea = getHuntingArea();
			}
		} else {
			huntingArea *= 2;
		}
	}

	// checks if there is something uneatable close by
	// in movement direction
	private void closeToDanger() {
		// MyDebug.d("Enemy: danger");
		final Vector2 center = getBody().getWorldCenter();
		final float rad = (getRadius() + 20)
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
		physicsWorld.rayCast(new RayCastCallback(){

			@Override
			public float reportRayFixture(final Fixture fixture, final Vector2 point,
					final Vector2 normal, final float fraction) {
				if(fixture.getBody().getUserData() != null){
					final Entity entity = (Entity)fixture.getBody().getUserData();
					if(!canEat(entity)){
						stopMovement();
					}
				}
				return 0;
			}
			
		}, this.getBody().getWorldCenter(),null );

	}

	private void stopMovement() {
		this.getBody().setLinearVelocity(new Vector2());
		this.getBody().setAngularVelocity(0);

	}

	private void retreat(final Entity danger) {
		MyDebug.d("Enemy: Retreat");
		applyMovement(new Vector2((this.getCenterX() - danger.getCenterX()),
				(this.getCenterY() - danger.getCenterY())));

	}

	private void applyMovement(Vector2 movementVector) { // NOPMD
		// the vector from enemy to the player

		movementVector = movementVector.mul(0.001f);

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

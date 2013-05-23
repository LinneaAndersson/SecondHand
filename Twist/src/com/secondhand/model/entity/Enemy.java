package com.secondhand.model.entity;

import java.util.List;
import java.util.Random;

import com.secondhand.model.physics.Vector2;
import com.secondhand.model.util.RandomUtil;

public class Enemy extends BlackHole {

	private static final float ENEMY_MAX_SPEED = 2;
	private static final float MAX_SIZE = 40;
	private static final float MIN_SIZE = 20;
	private static final float AREA_MULTIPLIER = 60;
	private static final float DANGER_MARGIN = 5;

	private float maxSpeed;

	@Override
	public void onPhysicsAssigned() {
	}

	public Enemy(final Vector2 vector, final float radius) {
		super(vector, radius, 0);
		this.maxSpeed = ENEMY_MAX_SPEED;
	}



	public float getHuntingArea() {
		return getRadius() * getRadius() * (float) Math.PI * AREA_MULTIPLIER;
	}

	public static float getMaxSize() {
		return MAX_SIZE;
	}

	public static float getMinSize() {
		return MIN_SIZE;
	}

	@Override
	protected void onGrow() {
	}

	public float getDangerArea() {
		return (getRadius() * getRadius() * (float) Math.PI) + DANGER_MARGIN;
	}

	// Cannot be negativ!!
	public void setMaxSpeed(final float maxSpeed) {
		if (maxSpeed < 0)
			throw new AssertionError();
		this.maxSpeed = maxSpeed;
	}


	public float getMaxSpeed() { return maxSpeed; }

	private float getVelocity (){
		return physics.getVelocity();
	}

	// player has highest chase-priority
	public void moveEnemy(final Entity player, final List<Entity> entityList) {
		if(this.getVelocity() == 0){
			final Random rng = new Random();
			float randomX = RandomUtil.nextFloat(rng, -maxSpeed, maxSpeed);
			float randomY = RandomUtil.nextFloat(rng,
					-((float)Math.sqrt(maxSpeed * maxSpeed - randomX * randomX)),
					((float)Math.sqrt(maxSpeed * maxSpeed - randomX * randomX)));
			physics.applyImpulse(new Vector2(randomX, randomY),maxSpeed);
		}
		danger(player, entityList);
		if (isCloseToEntity(player, getHuntingArea()) && canEat(player)) {
			dangerCheck(player);
		} else {
			dangerCheck(getHighesPriority(entityList));
		}
	}

	private void danger(final Entity player, final List<Entity> entityList) {
		if (isCloseToEntity(player, getDangerArea()) && !canEat(player)) {
			retreatFrom(player);
		} else {
			for (final Entity e : entityList) {
				if (e instanceof Enemy && isCloseToEntity(e, getDangerArea())
						&& !canEat(e)) {

					retreatFrom(e);
				}
			}
		}

	}

	// checks if the entity is within the specified margin
	private boolean isCloseToEntity(final Entity entity, final float margin) {

		final float dx = entity.getCenterX() - this.getCenterX();

		final float dy = entity.getCenterY() - this.getCenterY();

		return dx * dx + dy * dy <= margin;
	}

	// chase after smallest entity first
	// could create many get-something and make different
	// enemies behave different ( getClosest... )
	private Entity getHighesPriority(final List<Entity> entityList) {
		Entity entity = null;
		for (final Entity e : entityList) {
			if (e instanceof CircleEntity
					&& isCloseToEntity(e, getHuntingArea()) && canEat(e)) {
				if (!(entity instanceof Enemy && !(e instanceof Enemy))) {
					entity = getSmaller(entity, e);
				}
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
		if (entity != null && this.physics.isStraightLine(entity, this)) {
			physics.applyImpulse(new Vector2(
					entity.getCenterX() - getCenterX(), entity.getCenterY()
					- getCenterY()).mul(0.002f), getMaxSpeed());
		}
	}

	public void retreatFrom(final Entity danger) {
		physics.applyImpulse(new Vector2(getCenterX() - danger.getCenterX(),
				getCenterY() - danger.getCenterY()).mul(0.02f), getMaxSpeed());
	}

}

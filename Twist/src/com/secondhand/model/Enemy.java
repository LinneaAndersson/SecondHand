package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;

public class Enemy extends BlackHole {

	public Enemy(final Vector2 vector, final float radius,
			final PhysicsWorld physicsWorld, final float maxSpeed) {
		super(vector, radius, physicsWorld, true, maxSpeed);
	}

	// player has highest priority
	public void moveEnemy(Entity player, List<Entity> entityList) {
		if (isCloseToEntity(player) && canEat(player)) {
			applyMovement(player);
		} else {
			applyMovement(getHighesPriority(entityList));
		}

	}

	// may not be needed if enemy should just go after anything edible
	// perhaps implement a priority something here
	private boolean straightToEntity(final Entity entity) {

		return true;
	}

	// checks if enemy is close enough to start chasing
	// the entity
	private boolean isCloseToEntity(final Entity entity) {

		final float dx = entity.getCenterX() - this.getCenterX();

		final float dy = entity.getCenterY() - this.getCenterY();

		// the hunting area is tmp like this, don't know
		// why i choose area*100
		return dx * dx + dy * dy <= this.getArea() * 100;

	}

	// chase after smallest entity first
	// could create many is-something and make different
	// enemies behave different 
	private Entity getHighesPriority(List<Entity> entityList) {
		Entity entity = null;
		for (Entity e : entityList) {
			if (isCloseToEntity(e) && canEat(e) && isSmaller(entity, e)) {
				entity = e;
			}
		}
		return entity;
	}

	private boolean isSmaller(Entity entity, Entity e) {
		if (entity == null) {
			return true;
		}
		return e.getArea() <= entity.getArea();
	}

	// responsible for moving the enemies
	// at first we only have them moving straight at the player,
	// later we can add more functionality
	// TODO avoid larger stuff, chase smaller stuff
	// move in a smart way(no suicide)
	private void applyMovement(Entity entity) {

		if (straightToEntity(entity) && entity != null) {
			// the vector from enemy to the player
			Vector2 movementVector = new Vector2(
					(entity.getCenterX() - this.getCenterX()),
					entity.getCenterY() - this.getCenterY());

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

		} else {
			// (Avoid) somehow move the enemy around
			// larger entities
		}

	}

}

package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;

public class Enemy extends BlackHole {

	public Enemy(final Vector2 vector, final float radius,
			final PhysicsWorld physicsWorld, final float maxSpeed) {
		super(vector, radius, physicsWorld, true, maxSpeed);
	}

	// player has highest chase-priority
	public void moveEnemy(final Entity player, final List<Entity> entityList) {
		if (isCloseToEntity(player) && canEat(player)) {
			applyMovement(player);
		} else {
			applyMovement(getHighesPriority(entityList));
		}

	}

	// may be needed depending on search area size
	// true if road to entity is clear. also true 
	// if blocking entity is edible
	
	// smallest containing square and check with the squares 
	// area each step towards the entity. each step is 
	// the length of the squares side

	// #: steps, e: enemy, 0: entity.
	//  e ### 0 -> ok ok ok e0
	
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
	// could create many get-something and make different
	// enemies behave different ( getClosest... )
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
		if (entity == null || e.getArea() < entity.getArea()) {
			return e;
		} else {
			return entity;
		}
	}

	// responsible for moving the enemies
	// at first we only have them moving straight at the player,
	// later we can add more functionality
	// TODO avoid larger stuff, chase smaller stuff
	// move in a smart way(no suicide)
	private void applyMovement(final Entity entity) {
		
		// TODO change the null-check to something nicer
		// !!! straightToEntity() can take a null this way !!!
		// ok for now because that method doesn't do anything
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

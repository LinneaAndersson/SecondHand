package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;

public class Enemy extends BlackHole {

	public Enemy(final Vector2 vector, final float radius,
			final PhysicsWorld physicsWorld) {
		super(vector, radius, physicsWorld, true);
	}

	// checks if there is a straight line to entity
	// with nothing blocking

	// may not be needed if enemy should just go after anything edible
	private boolean straightToEntity(final Entity entity) {

		return true;
	}

	// checks if enemy is close enough to start chasing
	// the player
	public boolean isCloseToEntity(final Entity entity) {

		final float dx = entity.getCenterX() - this.getCenterX();

		final float dy = entity.getCenterY() - this.getCenterY();
		
		// the hunting area is tmp like this, don't know 
		// why i choose area*100
		return dx * dx + dy * dy <= this.getArea() * 100;

	}

	// responsible for moving the enemies
	// at first we only have them moving straight at the player,
	// later we can add more functionality
	// TODO avoid larger stuff, chase smaller stuff
	// move in a smart way(no suicide)
	public void moveEnemy(final Entity entity) {

		if (isCloseToEntity(entity)) {
			if (straightToEntity(entity) && canEat(entity)) {
				MyDebug.d("Here we should get movement");
				// the vector from enemy to the player
				Vector2 movementVector = new Vector2(
						(entity.getCenterX() - this.getCenterX()),
						entity.getCenterY() - this.getCenterY());

				// need to slow them down, they are to dam fast
				// otherwise
				// if(body is moving) we need mul(0.001)
				// for better mobility(so they won't just
				// go forward)
				final Vector2 testVector = new Vector2(this.getBody()
						.getLinearVelocity());
				if (testVector.add(movementVector).len() > 0) {
					movementVector = movementVector.mul(0.001f);
				} else {
					movementVector = movementVector.mul(0.0001f);
				}

				this.getBody().applyLinearImpulse(movementVector,
						this.getBody().getWorldCenter());

			} else {
				// (Avoid) somehow move the enemy around
				// larger entities
			}

		} else {
			// some survival instincts here
		}

	}
}

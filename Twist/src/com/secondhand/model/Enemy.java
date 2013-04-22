package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public class Enemy extends BlackHole {

	public Enemy(final Vector2 vector, final float radius) {
		super(vector, radius);
	}
	
	// will be quite easy to change player to a list instead

	// checks if there is a straight line to player
	// with nothing blocking

	// if we only want them to chase the player then
	// this isn't really necessary unless you increase
	// the search area.
	private boolean straightToPlayer(Player player) {

		return true;
	}

	// checks if enemy is close enough to start chasing
	// the player
	public boolean isCloseToPlayer(Player player) {

		float dx = player.getCenterX() - this.getCenterX();

		float dy = player.getCenterY() - this.getCenterY();

		return dx * dx + dy * dy <= this.getArea() * 100;

	}
	
	// responsible for moving the enemies
	// at first we only have them moving straight at the player,
	// later we can add more functionality
	// TODO avoid larger stuff, chase smaller stuff
	// move in a smart way(no suicide)
	public void moveEnemy(Player player) {

		if (isCloseToPlayer(player)) {
			if (straightToPlayer(player)) {

				// the vector from enemy to the player
				Vector2 movementVector = new Vector2(
						(player.getCenterX() - this.getCenterX()),
						player.getCenterY() - this.getCenterY());

				// need to slow them down, they are to dam fast
				// otherwise
				movementVector = movementVector.mul(0.0001f);
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

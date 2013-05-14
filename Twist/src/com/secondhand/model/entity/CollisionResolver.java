package com.secondhand.model.entity;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.powerup.*;

public final class CollisionResolver {

	private final GameWorld gameWorld;

	public CollisionResolver(final GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	private void handleBlackHoleCollision(final Entity entityA,
			final Entity entityB) {

		BlackHole blackHole;
		Entity other;
		if (entityA instanceof BlackHole) {
			blackHole = (BlackHole) entityA;
			if(entityB instanceof PowerUp && entityA instanceof Player){
				MyDebug.d("In handlelackHoleCollision 1");
				((PowerUp) entityB).activatePowerUp();
				MyDebug.d("In handlelackHoleCollision 1");
			}
			other = entityB;
		} else {
			if(entityA instanceof PowerUp && entityB instanceof Player){
				MyDebug.d("In handlelackHoleCollision 2");
				((PowerUp) entityA).activatePowerUp();
				MyDebug.d("In handlelackHoleCollision 2");
			}
			other = entityA;
			blackHole = (BlackHole) entityB;
		}
		MyDebug.d("In handlelackHoleCollision");
		blackHole.eatEntity(other);
		MyDebug.d("In handlelackHoleCollision");
	}

	public void checkCollision(final Object a, final Object b) {
		
		// if one or both is null, then we are dealing with a collision
		// involving one or
		// two non-entities
		// (ie, a black hole collides with the wall),
		// and we are not interested in handling such a collision
		if (a == null || b == null) {
			// if player collided with wall
			if (a instanceof Player || b instanceof Player) {
				this.gameWorld.getPropertyChangeSupport().firePropertyChange(
						"PlayerWallCollision", false, true);
				
			}
			return;
		}

		// now we know both the bodies are entities.
		final Entity entityA = (Entity) a;
		final Entity entityB = (Entity) b;

		// collisions involving black holes are the only ones we're interested
		// in.
		if (entityA instanceof BlackHole || entityB instanceof BlackHole) {
			handleBlackHoleCollision(entityA, entityB);
		}
	}

}

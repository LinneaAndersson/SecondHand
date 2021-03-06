package com.secondhand.model.entity;

import com.secondhand.model.physics.ICollisionResolver;

public final class CollisionResolver implements ICollisionResolver {

	private final IGameWorld gameWorld;

	public CollisionResolver(final IGameWorld gameWorld2) {
		this.gameWorld = gameWorld2;
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

	private void handleBlackHoleCollision(final Entity entityA,
			final Entity entityB) {

		BlackHole blackHole;
		Entity other;

		if (entityA instanceof BlackHole) {
			blackHole = (BlackHole) entityA;
			if (entityB instanceof IPowerUp && entityA instanceof Player) {
				final IPowerUp powerUp = (IPowerUp) entityB;
				gameWorld.getPowerUpList().add(powerUp);
			}
			other = entityB;
		} else {
			if (entityA instanceof IPowerUp && entityB instanceof Player) {
				final IPowerUp powerUp = (IPowerUp) entityA;
				gameWorld.getPowerUpList().add(powerUp);
			}
			other = entityA;
			blackHole = (BlackHole) entityB;
		}

		// enemies cannot eat powerups.
		if (other instanceof IPowerUp && blackHole instanceof Enemy)
			return;

		blackHole.eatEntity(other);
	}

}

package com.secondhand.model;


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
			other = entityB;
		} else {
			other = entityA;
			blackHole = (BlackHole) entityB;
		}

		blackHole.eatEntity(other);
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

package com.secondhand.model;

import com.secondhand.view.resource.Sounds;

public final class CollisionResolver {

	private CollisionResolver() {
	}

	private static void handleBlackHoleCollision(final Entity entityA,
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

	public static void checkCollision(final Object a, final Object b) {
		// if one or both is null, then we are dealing with a collision
		// involving one or
		// two non-entities
		// (ie, a black hole collides with the wall),
		// and we are not interested in handling such a collision
		if (a == null || b == null) {
			// if player collided with wall
			if (a instanceof Player || b instanceof Player)
				Sounds.getInstance().obstacleCollisionSound.play();
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

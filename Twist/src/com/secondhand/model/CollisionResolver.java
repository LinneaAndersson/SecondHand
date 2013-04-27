package com.secondhand.model;

import com.badlogic.gdx.physics.box2d.Contact;

public final class CollisionResolver {

	private CollisionResolver() { }
	
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
	

	public static void checkCollision(final Contact contact) {
		// if one or both is null, then we are dealing with a collision
		// involving one or
		// two non-entities
		// (ie, a black hole collides with the wall),
		// and we are not interested in handling such a collision
		if (contact.getFixtureA().getBody().getUserData() == null
				|| contact.getFixtureB().getBody().getUserData() == null) {
			return;
		}
		
		// now we know both the bodies are entities.
		final Entity entityA = (Entity) contact.getFixtureA().getBody()
				.getUserData();
		final Entity entityB = (Entity) contact.getFixtureB().getBody()
				.getUserData();

		
		// collisions involving black holes are the only ones we're interested in.
		if (entityA instanceof BlackHole || entityB instanceof BlackHole) {
			handleBlackHoleCollision(entityA, entityB);
		} 
	}

}

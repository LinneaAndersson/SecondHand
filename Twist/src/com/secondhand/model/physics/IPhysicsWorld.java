package com.secondhand.model.physics;

import java.util.Random;

public interface IPhysicsWorld {

	// Make your Entities stay on the screen
	void setWorldBounds(final int levelWidth, final int levelHeight);

	// Remove your WorldBounds.
	void removeWorldBounds();

	// checks if the rectangular area i UnOccupied
	boolean isAreaUnOccupied(final float x, final float y, final float r);

	Vector2 getRandomUnOccupiedArea(final int worldWidth,
			final int worldHeight, final float r, final Random rng);

	// making this a listener of PhysicsWorld
	void setContactListener();

	void unsetContactListener();

	// connect this to gameWorld
	void setCollisionResolver(final ICollisionResolver collisionResolver);
}

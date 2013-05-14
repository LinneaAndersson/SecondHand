package com.secondhand.model.physics;

import com.secondhand.model.entity.IGameWorld;

public interface IPhysicsWorld {

	// Make your Entities stay on the screen
	void setWorldBounds(final int levelWidth, final int levelHeight);

	// Remove your WorldBounds.
	void removeWorldBounds();

	// checks if the rectangular area i UnOccupied
	boolean isAreaUnOccupied(final float x, final float y, final float r);

	// making this a listener of PhysicsWorld
	void setContactListener();

	// connect this to gameWorld
	void setGameWorld(final IGameWorld gameWorld);
}

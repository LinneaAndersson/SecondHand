package com.secondhand.model.physics;

import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.GameWorld;

public interface IPhysicsWorld {

	// Make your Entities stay on the screen
	void setWorldBounds(final int levelWidth, final int levelHeight);

	// Remove your WorldBounds.
	void removeWorldBounds();

	// checks if enemy has staight line to the entity
	boolean isStraightLine(final Entity entity, final Enemy enemy);

	// checks if the rectangular area i UnOccupied
	boolean isAreaUnOccupied(final float x, final float y, final float r);

	// making this a listener of PhysicsWorld
	void setContactListener();

	// connect this to gameWorld
	void setGameWorld(final GameWorld gameWorld);
}

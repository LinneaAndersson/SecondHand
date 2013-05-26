package com.secondhand.model.physics;

// interface for game-entites to be used togheter with
// certain physics classes
public interface IPhysicsObject {

	float getCenterX();

	float getCenterY();

	float getRadius();

	boolean isEdible();
}

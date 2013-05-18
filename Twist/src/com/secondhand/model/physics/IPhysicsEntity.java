package com.secondhand.model.physics;

import java.util.List;



public interface IPhysicsEntity {
	
	float getCenterX();
	
	float getCenterY();
	
	void deleteBody();

	/// apply impulse to world center. 
	void applyImpulse(Vector2 impulsePosition, float maxSpeed);

	void applyImpulse(Vector2 impulse, Vector2 impulsePosition);
	
	void setLinearDamping(float f);
	
	// detach from andengine rendering. 
	void detachSelf();

	float computePolygonRadius(final List<Vector2> polygon);

	void setTransform(final Vector2 position);
	
	void stopMovment();
	
	// checks if enemy has staight line to the entity
	boolean isStraightLine(final IPhysicsObject entity, final IPhysicsObject enemy);

}


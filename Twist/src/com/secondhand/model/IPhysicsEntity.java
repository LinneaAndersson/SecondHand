package com.secondhand.model;

import java.util.List;

public interface IPhysicsEntity {
	
	float getCenterX();
	
	float getCenterY();
	
	float getRadius();
	
	void setRadius(final float radius);
	
	
	void deleteBody();

	/// apply impulse to world center. 
	void applyImpulse(Vector2 impulsePosition, float maxSpeed);

	void applyImpulse(Vector2 impulsePosition, Vector2 impulse);
	
	void setLinearDamping(float f);
	
	// detach from andengine rendering. 
	void detachSelf();

	float computePolygonRadius(final List<Vector2> polygon);

	void setTransform(final Vector2 position);
	
	void stopMovment();

}


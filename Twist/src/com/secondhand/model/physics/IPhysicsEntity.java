package com.secondhand.model.physics;

import java.util.List;

import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.Entity;


public interface IPhysicsEntity {
	
	float getCenterX();
	
	float getCenterY();
	
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
	
	// checks if enemy has staight line to the entity
	boolean isStraightLine(final Entity entity, final Enemy enemy);

}


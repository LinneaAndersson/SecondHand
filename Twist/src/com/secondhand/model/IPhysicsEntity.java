package com.secondhand.model;

import com.badlogic.gdx.physics.box2d.Body;

public interface IPhysicsEntity {
	
	float getCenterX();
	
	float getCenterY();
	
	float getRadius();
	
	void setRadius(final float radius);
	
	void deleteBody(boolean scheduledBody);

	void applyImpulse(float posX, float posY, float maxSpeed);

	// this does not belong in the model, remove
	Body getBody();

	void setLinearDamping(float f);
	
	// detach from andengine rendering. 
	void detachSelf();
}

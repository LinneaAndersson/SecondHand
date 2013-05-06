package com.secondhand.model;

import org.anddev.andengine.entity.shape.IShape;

import com.badlogic.gdx.physics.box2d.Body;

public interface IPhysicsEntity {
	
	float getCenterX();
	
	float getCenterY();
	
	void registerBody(final Entity entity, final Body body, final IShape shape);
	
	void deleteBody(boolean scheduledBody);

	void applyImpulse(float posX, float posY, float maxSpeed);

	// this does not belong in the model, remove
	Body createType(IShape shape, Entity entity);

	// this does not belong in the model, remove
	Body getBody();

	void setLinearDamping(float f);
}

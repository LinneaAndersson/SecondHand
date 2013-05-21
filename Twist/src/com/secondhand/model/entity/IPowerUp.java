package com.secondhand.model.entity;

import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.resource.PowerUpType;

public interface IPowerUp extends IPhysicsObject {

	PowerUpType getPowerUpType();
	
	float getDuration();
	
	void activateEffect(final Player player);
	
	boolean hasText();
	
	String getText();
	
	void resetPlayerColor(final Player player);
	
	float getR();
	float getG();
	float getB();

	void activatePowerUp(final Player player);

	void deactivateEffect(final Player player, boolean hasAnother);
	
	// frequency of this powerup. Given on a scale from 1-10.
	// where 10 is dirt common and 1 is super rare. 
	// static now, but must be specified for every powerup.
	//int getFrequency();
}

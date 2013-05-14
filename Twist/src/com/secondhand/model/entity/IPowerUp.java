package com.secondhand.model.entity;

import com.secondhand.model.resource.PowerUpType;

public interface IPowerUp {

	PowerUpType getPowerUpType();
	
	float getDuration();
	
	void activateEffect(final Player player);
	
	void deactivateEffect(final Player player);
	
	boolean hasText();
	
	String getText();
	
	boolean hasAnother(final Player player);
	
	float getR();
	float getG();
	float getB();

	void eaten();
	
	// rarity of this powerup. Given on a scale from 1-10.
	// where 1 is dirt common and 10 is super rare. 
	int getRarity();
}

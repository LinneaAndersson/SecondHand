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
}

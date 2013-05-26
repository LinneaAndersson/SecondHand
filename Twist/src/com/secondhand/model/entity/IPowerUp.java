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

	void activatePowerUp(final Player player, boolean hasBlackColor);

	void deactivateEffect(final Player player, boolean hasAnother);

}

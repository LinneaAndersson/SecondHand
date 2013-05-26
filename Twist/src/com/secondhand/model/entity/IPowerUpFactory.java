package com.secondhand.model.entity;

import java.util.Random;

import com.secondhand.model.physics.Vector2;

public interface IPowerUpFactory {

	IPowerUp getRandomPowerUp(final Vector2 position);

	void setRandom(final Random rng);

}

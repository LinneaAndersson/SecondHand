package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public class Obstacle extends Entity {

	public Obstacle(Vector2 position, float radius) {
		// TODO: pass down the shape of the obstacle
		super(position,radius, null);
	}
}

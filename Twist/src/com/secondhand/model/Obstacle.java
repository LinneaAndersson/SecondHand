package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.TexturedPolygon;

public class Obstacle extends Entity {

	public Obstacle(Vector2 position, TexturedPolygon polygon) {
		// TODO: pass down the shape of the obstacle
		super(position,
				0, // we'll set the radius to 0 and ignore it for this class.
				polygon);
	}
}

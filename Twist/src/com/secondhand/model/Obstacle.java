package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.TexturedPolygon;

public class Obstacle extends PolygonEntity {

	public Obstacle(Vector2 position, TexturedPolygon polygon) {
		super(position,polygon, false);
	}
}

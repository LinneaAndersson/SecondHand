package com.secondhand.model.entity;

import java.util.List;

import com.secondhand.model.physics.Vector2;

public class Obstacle extends PolygonEntity {
	
	
	public Obstacle(final Vector2 position,  final List<Vector2> polygon) {
		super(position, polygon, false);
	}
	
	@Override
	public float getScoreWorth() {
		// obstacles are hard to eat, so you should get lots of points for eating them!
		return 2;
	}
}


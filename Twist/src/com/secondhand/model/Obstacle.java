package com.secondhand.model;

import java.util.List;

public class Obstacle extends PolygonEntity {
	
	
	public Obstacle(final Vector2 position,  final List<Vector2> polygon, final GameWorld level) {
		super(position, polygon, false, level);
	}
	
	@Override
	public float getScoreWorth() {
		// obstacles are hard to eat, so you should get lots of points for eating them!
		return 2;
	}
}

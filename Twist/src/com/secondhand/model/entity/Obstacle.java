package com.secondhand.model.entity;

import java.util.List;

import com.secondhand.model.physics.Vector2;

public class Obstacle extends PolygonEntity {

	private final static float SCORE_WORTH = 2;

	public Obstacle(final Vector2 position, final List<Vector2> polygon) {
		super(position, polygon, false);
	}

	@Override
	protected float getScoreWorth() {
		return SCORE_WORTH;
	}
}

package com.secondhand.model.entity;

import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PlanetType;

public class Planet extends CircleEntity {

	private final PlanetType planetType;
	private final static float SCORE_WORTH = 1;
	
	public Planet (final Vector2 position, final float radius, final PlanetType planetType) {
		super(position, radius, true);
		this.planetType = planetType;
	}
	
	public PlanetType getPlanetType() {
		return this.planetType;
	}
	
	@Override
	protected float getScoreWorth() {
		return SCORE_WORTH;
	}
}
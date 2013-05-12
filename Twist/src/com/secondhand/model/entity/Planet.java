package com.secondhand.model.entity;

import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PlanetType;

public class Planet extends CircleEntity {

	private final PlanetType planetType;
	
	//new TexturedCircle(position.x, position.y, radius, TextureRegions.getInstance().getPlanetTexture(planetType))
	
	public Planet (final Vector2 position, final float radius, final PlanetType planetType, final GameWorld level) {
		super(position, radius, true, level);
		this.planetType = planetType;
	}
	
	public PlanetType getPlanetType() {
		return this.planetType;
	}
	
	@Override
	public float getScoreWorth() {
		return 1;
	}
}
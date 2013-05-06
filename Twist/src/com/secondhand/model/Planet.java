package com.secondhand.model;

import com.secondhand.model.resource.PlanetType;
import com.secondhand.view.opengl.TexturedCircle;
import com.secondhand.view.resource.TextureRegions;

public class Planet extends CircleEntity {

	public Planet (final Vector2 position, final float radius, final PlanetType planetType, final GameWorld level) {
		super(new TexturedCircle(position.x, position.y, radius, TextureRegions.getInstance().getPlanetTexture(planetType)), true, level);
	}
	
	@Override
	public float getScoreWorth() {
		return 1;
	}
	
	
}
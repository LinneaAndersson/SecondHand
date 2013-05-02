package com.secondhand.model;


import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.resource.PlanetType;
import com.secondhand.model.resource.TextureRegions;
import com.secondhand.view.opengl.Circle;
import com.secondhand.view.opengl.TexturedCircle;

public class Planet extends CircleEntity {

	public Planet (final Vector2 position, final float radius, final PlanetType planetType, final GameWorld level) {
		super(new TexturedCircle(position.x, position.y, radius, TextureRegions.getInstance().getPlanetTexture(planetType)), true, level,
				FixtureDefs.PLANET_FIXTURE_DEF);
	}
	
	/**
	 * Create a colored planet. This constructor is much easier to test.
	 */
	public Planet (final Vector2 position, final float radius, final GameWorld level) {
		super(new Circle(position.x, position.y, radius), true, level, FixtureDefs.PLANET_FIXTURE_DEF);
	}
	
	@Override
	public float getScoreWorth() {
		return 1;
	}
	
	
}
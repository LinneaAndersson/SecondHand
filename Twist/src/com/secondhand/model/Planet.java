package com.secondhand.model;


import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;
import com.secondhand.opengl.TexturedCircle;
import com.secondhand.resource.PlanetType;
import com.secondhand.resource.TextureRegions;

public class Planet extends CircleEntity {

	public Planet (final Vector2 position, final float radius, final PlanetType planetType, final Level level) {
		super(new TexturedCircle(position.x, position.y, radius, TextureRegions.getInstance().getPlanetTexture(planetType)), true, level, true,
				FixtureDefs.PLANET_FIXTURE_DEF);
	}
	
	/**
	 * Create a colored planet. This constructor is much easier to test.
	 */
	public Planet (final Vector2 position, final float radius, final Level level) {
		super(new Circle(position.x, position.y, radius), true, level, true, FixtureDefs.PLANET_FIXTURE_DEF);
	}
	
	@Override
	public float getScoreWorth() {
		return 1;
	}
}
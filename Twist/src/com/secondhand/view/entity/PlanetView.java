package com.secondhand.view.entity;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.Planet;
import com.secondhand.view.opengl.TexturedCircle;
import com.secondhand.view.physics.FixtureDefs;
import com.secondhand.view.resource.TextureRegions;

public class PlanetView extends CircleView {

	public PlanetView(final PhysicsWorld physicsWorld,
			final Planet planet) {
		super(physicsWorld, planet, new TexturedCircle(planet.getInitialPosition().x, planet.getInitialPosition().y, planet.getRadius(),
				TextureRegions.getInstance().getPlanetTexture(planet.getPlanetType())),
				FixtureDefs.PLANET_FIXTURE_DEF);
	}
}
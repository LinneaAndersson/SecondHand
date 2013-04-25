package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;
import com.secondhand.opengl.TexturedCircle;
import com.secondhand.resource.PlanetType;
import com.secondhand.resource.TextureRegions;

public class Planet extends CircleEntity {

	public Planet (final Vector2 position, final float radius, final PlanetType planetType, final PhysicsWorld physicsWorld) {
		super(new TexturedCircle(position.x, position.y, radius, TextureRegions.getInstance().getPlanetTexture(planetType)), true, physicsWorld, true,
				FixtureDefs.PLANET_FIXTURE_DEF);
	}
	
	/**
	 * Create a colored planet. This constructor is much easier to test.
	 */
	public Planet (final Vector2 position, final float radius, final PhysicsWorld physicsWorld) {
		super(new Circle(position.x, position.y, radius), true, physicsWorld, true, FixtureDefs.PLANET_FIXTURE_DEF);
	}
}
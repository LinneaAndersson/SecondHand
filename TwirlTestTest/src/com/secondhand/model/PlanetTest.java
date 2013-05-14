package com.secondhand.model;

import junit.framework.TestCase;

import com.secondhand.model.entity.Planet;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PlanetType;

public class PlanetTest extends TestCase{

	public void testConstructor() {
		final Vector2 position = new Vector2();
		final float radius = 10;
		final PlanetType planetType = PlanetType.BLOOD;
		
		final Planet planet = new Planet(position, radius, planetType);
		
		assertEquals(position, planet.getInitialPosition());
		assertEquals(radius, planet.getRadius());
		assertEquals(planetType, planet.getPlanetType());
	}
}

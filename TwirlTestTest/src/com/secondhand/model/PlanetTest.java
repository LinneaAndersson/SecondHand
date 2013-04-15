package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

import junit.framework.TestCase;

public class PlanetTest extends TestCase{

	public void testConstructor() {

		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Planet planet = new Planet(pos, rad);
		
		assertEquals(rad, planet.getRadius());
		assertEquals(pos.x, planet.getPosition().x);
		assertEquals(pos.y, planet.getPosition().y);
	}
	
}

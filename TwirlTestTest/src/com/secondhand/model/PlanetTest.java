package com.secondhand.model;

import junit.framework.TestCase;

import org.anddev.andengine.opengl.buffer.BufferObjectManager;

import com.badlogic.gdx.math.Vector2;

public class PlanetTest extends TestCase{

	private final BufferObjectManager mBufferObjectManager = new BufferObjectManager();
	
	public void setUp() {
		// if we don't do this, an exception is thrown when calling the constructor of Circle.
		BufferObjectManager.setActiveInstance(mBufferObjectManager);
	}
	
	public void testConstructor() {

		final GameWorld gW = new GameWorld();		
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Planet planet = new Planet(pos, rad, gW);
		
		assertEquals(rad, planet.getRadius());
		assertEquals(pos.x, planet.getX());
		assertEquals(pos.y, planet.getY());
		assertEquals(true, planet.isEdible());
	}
	
}

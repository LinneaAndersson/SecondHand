package com.secondhand.physics;


import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.buffer.BufferObjectManager;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Planet;

import junit.framework.TestCase;

public class PhysicsAreaCheckerTest extends TestCase {

	private final BufferObjectManager mBufferObjectManager = new BufferObjectManager();

	public void setUp() {
		// if we don't do this, an exception is thrown when calling the constructor of Circle.
		BufferObjectManager.setActiveInstance(mBufferObjectManager);
	}

	// TODO: get this test to work:
/*
	public void testIsRectangleAreaUnoccupied() {

		PhysicsWorld pw = new PhysicsWorld(new Vector2(), true);
		new Planet(new Vector2(0,0), 10, pw);
		new Planet(new Vector2(40,0), 10, pw);
		
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(20, 0), 10, 10, pw));
		assertFalse(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(12, 0), 12, 10, pw));
	}*/
}

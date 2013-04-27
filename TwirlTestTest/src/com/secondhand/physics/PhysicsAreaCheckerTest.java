package com.secondhand.physics;


import junit.framework.TestCase;

import org.anddev.andengine.opengl.buffer.BufferObjectManager;

public class PhysicsAreaCheckerTest extends TestCase {

	private final BufferObjectManager mBufferObjectManager = new BufferObjectManager();

	public void setUp() {
		// if we don't do this, an exception is thrown when calling the constructor of Circle.
		BufferObjectManager.setActiveInstance(mBufferObjectManager);
	}
/*
	public void testIsRectangleAreaUnoccupied() {

		PhysicsWorld pw = new PhysicsWorld(new Vector2(), true);
		new Planet(new Vector2(1,1), 1, pw);
		new Planet(new Vector2(6,1), 1, pw);
		
			
		Iterator<Body> it = pw.getBodies();
		
		while(it.hasNext()) {
			Body body = it.next();
			MyDebug.d("body: " + body.getPosition().x *32f + " " + 
		body.getPosition().y * 32f+
				((CircleShape)body.getFixtureList().get(0).getShape()).getRadius() * 32f);
			
			body.getFixtureList().get(0).
		}
		
	
		
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 100), 100, 2, pw));
	
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 90), 100, 2, pw));
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 80), 100, 2, pw));
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 70), 100, 2, pw));
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 60), 100, 2, pw));
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 50), 100, 2, pw));
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 40), 100, 2, pw));
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 30), 100, 2, pw));
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 20), 100, 2, pw));
		assertTrue(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 10), 100, 2, pw));
		
		assertFalse(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(0, 00), 100, 2, pw));
		
		//assertFalse(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(3, 0), 4, 2, pw));
	}*/
}

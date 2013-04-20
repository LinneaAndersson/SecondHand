package com.secondhand.opengl;


import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.opengl.buffer.BufferObjectManager;

import junit.framework.TestCase;

public class CircleTest extends TestCase {

	private final BufferObjectManager mBufferObjectManager = new BufferObjectManager();

	public void setUp() {
		// if we don't do this, an exception is thrown when calling the constructor of Circle.
		BufferObjectManager.setActiveInstance(mBufferObjectManager);
	}


	public void testConstructor() {
		final float x = 1.3f;
		final float y = 5.1f;
		final float radius = 3.4f;
		final float diameter = radius * 2;
		Circle circle = new Circle(x, y, radius);

		assertEquals(x, circle.getX());
		assertEquals(y, circle.getY());
		assertEquals(radius, circle.getRadius());
		assertEquals(diameter, circle.getWidth());
		assertEquals(diameter, circle.getHeight());
	}

	public void testIsCulled() {
		final int WIDTH = 400;
		final int HEIGHT = 300;
		final float radius = 10;

		Camera camera = new Camera(0, 0, WIDTH, HEIGHT);
		Circle circle = new Circle(WIDTH - radius*2, HEIGHT - radius*2, radius);
		assertFalse(circle.isCulled(camera));
		
		circle = new Circle(WIDTH+1, HEIGHT+1 , radius);
		assertTrue(circle.isCulled(camera));
	}
}

package com.secondhand.model;

import com.secondhand.model.entity.CircleEntity;
import com.secondhand.model.physics.Vector2;

import junit.framework.TestCase;

public class CircleEntityTest extends TestCase {

	public void testGetSurfacePosition() {
		final float radius = (float) Math.random() * 1000;
		final float centerX = radius;
		final float centerY = radius;
		
		CircleEntity entity = new CircleEntity(new Vector2(), radius, true) {
			@Override
			protected float getScoreWorth() { return 0; }
			
			@Override
			public float getCenterX() {
				return centerX;
			}
			
			@Override
			public float getCenterY() {
				return centerY;
			}
		};
		
		final Vector2 relativePosition = new Vector2(centerX + radius*2, centerY);
		final Vector2 expectedSurfacePosition = new Vector2(centerX + radius, centerY);
		final Vector2 surfacePosition = entity.calculateSurfacePosition(relativePosition, false);
		assertTrue(expectedSurfacePosition.x == surfacePosition.x);
		assertTrue(expectedSurfacePosition.y == surfacePosition.y);
	}
	
}

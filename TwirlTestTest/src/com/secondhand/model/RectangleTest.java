package com.secondhand.model;

import junit.framework.TestCase;

import com.secondhand.model.entity.RectangleEntity;
import com.secondhand.model.physics.Vector2;

public class RectangleTest extends TestCase {
	private final static float SCORE_WORTH = 2;
	private final static float WIDTH = 10;
	private final static float HEIGHT = 15;
	private final static float POSX = 50;
	private final static float POSY = 30;
	private final static boolean EATABLE = true;

	public RectangleEntity getNewRectangleEntity(Vector2 position, float width,
			float height, boolean isEdible) {
		return new RectangleEntity(position, width, height, isEdible) {
			@Override
			public void onPhysicsAssigned() {
			}

			@Override
			protected float getScoreWorth() {
				return SCORE_WORTH;
			}
		};
	}

	public void testConstructor() {

		RectangleEntity rectangle = getNewRectangleEntity(new Vector2(POSX, POSY), WIDTH,
				HEIGHT, EATABLE);
		
		assertEquals(WIDTH, rectangle.getWidth());
		assertEquals(HEIGHT, rectangle.getHeight());
		assertEquals(POSX, rectangle.getCenterX());
		assertEquals(POSY, rectangle.getCenterY());
		assertEquals(SCORE_WORTH, rectangle.getScoreValue());
		assertEquals(EATABLE, rectangle.isEdible());
		
	}

}

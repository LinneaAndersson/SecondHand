package com.secondhand.model;

import java.util.List;

import junit.framework.TestCase;

import com.secondhand.model.entity.RectangleEntity;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.physics.Vector2;

public class RectangleEntityTest extends TestCase {
	private final static int SCORE_WORTH = 0;
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
	
	private class RectangleTestPhysicsEntity implements IPhysicsEntity {

		@Override
		public float getCenterX() {
			return POSX;
		}

		@Override
		public float getCenterY() {
			return POSY;
		}

		@Override
		public void deleteBody() {
		}

		@Override
		public void applyImpulse(Vector2 impulsePosition, float maxSpeed) {
		}

		@Override
		public void applyImpulse(Vector2 impulsePosition, Vector2 impulse) {
		}

		@Override
		public void setLinearDamping(float f) {
		}

		@Override
		public void detachSelf() {
		}

		@Override
		public float computePolygonRadius(List<Vector2> polygon) {
			return 0;
		}

		@Override
		public void setTransform(Vector2 position) {
		}

		@Override
		public void stopMovment() {
		}

		@Override
		public boolean isStraightLine(IPhysicsObject entity,
				IPhysicsObject enemy) {
			return false;
		}
	}

	public void testConstructor() {

		RectangleEntity rectangle = getNewRectangleEntity(new Vector2(POSX, POSY), WIDTH,
				HEIGHT, EATABLE);
		rectangle.setPhysics(new RectangleTestPhysicsEntity());
		
		assertEquals(WIDTH, rectangle.getWidth());
		assertEquals(HEIGHT, rectangle.getHeight());
		assertEquals(POSX, rectangle.getCenterX());
		assertEquals(POSY, rectangle.getCenterY());
		// Rectangles have no value.
		assertEquals(SCORE_WORTH, rectangle.getScoreValue());
		assertEquals(EATABLE, rectangle.isEdible());
		
	}

}

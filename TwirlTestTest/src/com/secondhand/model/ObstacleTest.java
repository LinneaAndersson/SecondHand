package com.secondhand.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.secondhand.model.entity.Obstacle;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.physics.Vector2;

public class ObstacleTest extends TestCase{
	
	private static final float RADIUS = 10;
	private static final int SCORE_WORTH = 2;
	private final ArrayList<Vector2> polygon = new ArrayList<Vector2>();
	private boolean correctArrayListPassed;
	
	private class ObstacleTestPhysicsEntity implements IPhysicsEntity {

		@Override
		public float getCenterX() {
			return 0;
		}

		@Override
		public float getCenterY() {
			return 0;
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
			correctArrayListPassed =  (polygon == ObstacleTest.this.polygon);
				
			return RADIUS;
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
		final Vector2 position = new Vector2();
		
		final Obstacle obstacle = new Obstacle(position, polygon);
		
		assertEquals(position, obstacle.getInitialPosition());
		assertEquals(polygon, obstacle.getPolygon());
	}
	
	public void testPolygonRadius() {
		final Vector2 position = new Vector2();
		
		final Obstacle obstacle = new Obstacle(position, polygon);

		obstacle.setPhysics(new ObstacleTestPhysicsEntity());
		assertEquals(RADIUS, obstacle.getRadius());
		assertTrue(correctArrayListPassed);
	}
	
	public void testGetScoreValue() {
		final Vector2 position = new Vector2();
		
		final Obstacle obstacle = new Obstacle(position, polygon);

		obstacle.setPhysics(new ObstacleTestPhysicsEntity());
		assertEquals((int)(RADIUS * SCORE_WORTH), obstacle.getScoreValue());
	}
 	
}

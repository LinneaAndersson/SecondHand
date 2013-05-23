package com.secondhand.model;



import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.List;

import com.secondhand.model.entity.Entity;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.physics.Vector2;

import junit.framework.TestCase;

public class EntityTest extends TestCase {
	
	private final static int RADIUS = 10;
	private final static int SCORE_WORTH = 2;
	
	private boolean deleteBodyCalled;
	
	public Entity getNewEntity(Vector2 position, boolean isEdible) {
		return new Entity(position,isEdible) {
			@Override
			public void onPhysicsAssigned() {}
			@Override
			public float getScoreWorth() { return SCORE_WORTH; }
			@Override
			public float getRadius() { return RADIUS; }
		};
	}
	
	private class EntityTestPhysicsEntity implements IPhysicsEntity {

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
			deleteBodyCalled = true;
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
		Vector2 position = new Vector2();
		boolean isEdible = true;
		
		Entity entity = getNewEntity(position, isEdible);
		
		assertEquals(position, entity.getInitialPosition());
		assertEquals(isEdible, entity.isEdible());
	}
	
	public void testGetScoreValue() {
		Entity entity = getNewEntity(new Vector2(), true);
		
		assertEquals(RADIUS * SCORE_WORTH, entity.getScoreValue());
	}
	
	public void testSetIsEdible() {
		Vector2 position = new Vector2();
		boolean isEdible = true;
		
		Entity entity = getNewEntity(position, isEdible);
		entity.setIsEdible(false);
		
		assertEquals(false, entity.isEdible());
	}
	
	public void testDeleteBody() {
		Vector2 position = new Vector2();
		boolean isEdible = true;
		
		Entity entity = getNewEntity(position, isEdible);
		
		entity.setPhysics(new EntityTestPhysicsEntity());
		this.deleteBodyCalled = false;
		entity.deleteBody();
		assertTrue(this.deleteBodyCalled);
	}
	
	private boolean propertyChangeSent;
	
	private class PropChangeTest implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			String name = event.getPropertyName();
			propertyChangeSent = name.equals(Entity.IS_SCHEDULED_FOR_DELETION);
		}
		
	}
	/*
	public void testScheduledForDeletion() {
		Vector2 position = new Vector2();
		boolean isEdible = true;
		
		Entity entity = getNewEntity(position, isEdible);
		entity.addListener(new PropChangeTest());
		propertyChangeSent = false;
		entity.
	}*/
	
	
}

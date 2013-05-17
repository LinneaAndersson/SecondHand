package com.secondhand.model;


import com.secondhand.model.entity.Entity;
import com.secondhand.model.physics.Vector2;

import junit.framework.TestCase;

public class EntityTest extends TestCase {
	
	private final static int RADIUS = 10;
	private final static int SCORE_WORTH = 2;
	
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
}

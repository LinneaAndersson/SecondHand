package com.secondhand.model;


import com.secondhand.model.entity.Entity;
import com.secondhand.model.physics.Vector2;

import junit.framework.TestCase;

public class EntityTest extends TestCase {
	
	public void testConstructor() {
		Vector2 position = new Vector2();
		boolean isEdible = true;
		
		Entity entity = new Entity(position,isEdible) {
			@Override
			public void onPhysicsAssigned() {}
			@Override
			public float getScoreWorth() {return 0;}
			@Override
			public float getRadius() {return 0;}
		};
		
		assertEquals(position, entity.getInitialPosition());
		assertEquals(isEdible, entity.isEdible());
	}
}

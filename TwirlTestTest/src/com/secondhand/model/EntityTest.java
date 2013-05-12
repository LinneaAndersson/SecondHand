package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.GameWorld;
import com.secondhand.model.physics.Vector2;
import com.secondhand.view.physics.MyPhysicsWorld;

import junit.framework.TestCase;

public class EntityTest extends TestCase {
	
	public void testConstructor() {
		Vector2 position = new Vector2();
		boolean isEdible = true;
		
		Entity entity = new Entity(position,isEdible,new GameWorld(new MyPhysicsWorld(new PhysicsWorld(new com.badlogic.gdx.math.Vector2(), true)))) {
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

package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

import junit.framework.TestCase;

public class EntityTest extends TestCase {

	public void testConstructor() {
		boolean isEdible = true;
		// TODO add test for Shape parameter
		Entity entity = new Entity(null, isEdible) {
			@Override
			public float getArea() {
				return 0;
			}
		};
		
		assertEquals(entity.isEdible(), isEdible);
	}
	
	public void testSetBody() {
		boolean isEdible = true;
		Entity entity = new Entity(null, isEdible) {
			@Override
			public float getArea() {
				return 0;
			}
		};
		
		Body testBody = new PhysicsWorld(new Vector2(),true).createBody(new BodyDef());
		entity.setBody(testBody);
		assertEquals(entity.getBody(), testBody);
	}
}
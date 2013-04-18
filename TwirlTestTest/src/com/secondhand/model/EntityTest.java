package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;

import junit.framework.TestCase;

public class EntityTest extends TestCase {

	public void testConstructor() {
		Vector2 v = new Vector2();
		float rad = 5;
		Entity entityA = new Entity(v, rad, new Circle(0, 0, 5),true) {};
		Entity entityB = new Entity(v, rad, new Circle(0, 0, 5),false) {};
		
		assertEquals(entityA.getRadius(), rad);
	}
	
}
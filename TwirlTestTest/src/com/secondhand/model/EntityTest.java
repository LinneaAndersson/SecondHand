package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;

import junit.framework.TestCase;

public class EntityTest extends TestCase {

	public void testConstructor() {
		Vector2 vector = new Vector2();
		float rad = 5;
		Circle circle = new Circle(0, 0, 5);
		Entity entityA = new Entity(vector, rad, circle,true) {};
		Entity entityB = new Entity(vector, rad, circle,false) {};
		
		assertEquals(entityA.getRadius(), rad);
		assertEquals(entityA.getShape(), circle);
		
		rad = 3;
		entityA.setRadius(rad);
		assertEquals(entityA.getRadius(), rad);
	}
	
}
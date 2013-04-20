package com.secondhand.model;

import junit.framework.TestCase;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.TexturedPolygon;

public class ObstacleTest extends TestCase{

	
	
	public void testConstructor() {
		
		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		// reaserch the polygons variables
		// before using this test
		TexturedPolygon tp = new TexturedPolygon(rad, rad, null, null);
		
		Obstacle obstacle = new Obstacle(pos, tp);
		
		assertEquals(rad, obstacle.getRadius());
		assertEquals(pos.x, obstacle.getX());
		assertEquals(pos.y, obstacle.getY());
	}
	
}

package com.secondhand.model;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.secondhand.model.entity.Obstacle;
import com.secondhand.model.physics.Vector2;

public class ObstacleTest extends TestCase{

	public void testConstructor() {
		final Vector2 position = new Vector2();
		final ArrayList<Vector2> polygon = new ArrayList<Vector2>();
		
		final Obstacle obstacle = new Obstacle(position, polygon);
		
		assertEquals(position, obstacle.getInitialPosition());
		assertEquals(polygon, obstacle.getPolygon());
	}
	
}

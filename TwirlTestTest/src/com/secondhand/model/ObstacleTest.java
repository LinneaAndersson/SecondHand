package com.secondhand.model;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.secondhand.model.entity.Obstacle;
import com.secondhand.model.physics.Vector2;

public class ObstacleTest extends TestCase{

	public void testConstructor() {
		Vector2 position = new Vector2();
		ArrayList<Vector2> polygon = new ArrayList<Vector2>();
		
		Obstacle obstacle = new Obstacle(position, polygon);		
	}
	
}

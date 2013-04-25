package com.secondhand.model;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import junit.framework.TestCase;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Polygon;

public class ObstacleTest extends TestCase{

	public void testConstructor() {
		
		final PhysicsWorld pw  =new PhysicsWorld(new Vector2(), true);
		
		
		Vector2 pos = new Vector2(2f, 4f);
		List<Vector2> points = new ArrayList<Vector2>();
		
		// simple triangle.
		points.add(new Vector2(0, 0));
		points.add(new Vector2(10, 0));
		points.add(new Vector2(0, 10));
		
		Polygon p = new Polygon(pos, points);
		
		Obstacle obstacle = new Obstacle(p, pw);
		
		assertEquals(pos.x, obstacle.getX());
		assertEquals(pos.y, obstacle.getY());
		assertEquals(false, obstacle.isEdible());
		
		assertEquals(0f, obstacle.getPolygon().getPolygon().get(0).x);
		assertEquals(0f, obstacle.getPolygon().getPolygon().get(0).y);
		
		assertEquals(10f, obstacle.getPolygon().getPolygon().get(1).x);
		assertEquals(0f, obstacle.getPolygon().getPolygon().get(1).y);
		
		assertEquals(0f, obstacle.getPolygon().getPolygon().get(2).x);
		assertEquals(10f, obstacle.getPolygon().getPolygon().get(2).y);
		
	}
	
}

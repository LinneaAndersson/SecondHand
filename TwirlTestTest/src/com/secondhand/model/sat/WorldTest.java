package com.secondhand.model.sat;

import java.util.ArrayList;
import java.util.List;


import com.badlogic.gdx.math.Vector2;


import junit.framework.TestCase;

public class WorldTest extends TestCase {
	
	public void testIsWithinWorldBounds() {
		World world = new World(100, 100);
		
		// for simple squares
		
		List<Vector2> edges = new ArrayList();
		edges.add(new Vector2(-20,-20));
		edges.add(new Vector2(0,-20));
		edges.add(new Vector2(0,0));
		edges.add(new Vector2(-20,0));
		
		World.Polygon polygon = new World.Polygon(new Vector2(20,20), edges);
		
		assertTrue(world.isUnoccupied(polygon));
		
		List<Vector2> edges2 = new ArrayList<Vector2>();
		edges2.add(new Vector2(0,0));
		edges2.add(new Vector2(20,0));
		edges2.add(new Vector2(20,20));
		edges2.add(new Vector2(0,20));
		World.Polygon polygon2 = new World.Polygon(new Vector2(10,10), edges2);
		
		assertTrue(world.isUnoccupied(polygon2));
		
		world.addToWorld(polygon);
		
		
		assertFalse(world.isUnoccupied(polygon2));
		
		World.Polygon polygon3 = new World.Polygon(new Vector2(30,10), edges2);
		assertTrue(world.isUnoccupied(polygon3));
		
	}
	
	public void testIsWithinWorldBounds2() {
		World world = new World(100, 100);
		
		// now do it for polygons
		
		List<Vector2> edges = new ArrayList();
		edges.add(new Vector2(0, 0));
		edges.add(new Vector2(3,1));
		edges.add(new Vector2(4,3));
		edges.add(new Vector2(3,4));
		edges.add(new Vector2(0,3));
		
		World.Polygon polygon = new World.Polygon(new Vector2(0,0), edges);
		
		assertTrue(world.isUnoccupied(polygon));
		
		List<Vector2> edges2 = new ArrayList();
		edges2.add(new Vector2(0,1));
		edges2.add(new Vector2(2,0));
		edges2.add(new Vector2(2,1));
		World.Polygon polygon2 = new World.Polygon(new Vector2(3,2), edges2);
		
		assertTrue(world.isUnoccupied(polygon2));
		
		world.addToWorld(polygon);
		
		assertFalse(world.isUnoccupied(polygon2));
	}
}
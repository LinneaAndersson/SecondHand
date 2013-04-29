package com.secondhand.model.sat;

import java.util.ArrayList;
import java.util.List;


import com.badlogic.gdx.math.Vector2;


import junit.framework.TestCase;

public class WorldTest extends TestCase {
	
	public void testIsWithinWorldBounds() {
		World world = new World(100, 100);
		
		List<Vector2> edges = new ArrayList();
		edges.add(new Vector2(-20,-20));
		edges.add(new Vector2(0,-20));
		edges.add(new Vector2(0,0));
		edges.add(new Vector2(-20,0));
		
		World.Polygon polygon = new World.Polygon(new Vector2(20,20), edges);
		
		assertTrue(world.isUnoccupied(polygon));
		
		List<Vector2> edges2 = new ArrayList();
		edges.add(new Vector2(0,0));
		edges.add(new Vector2(20,0));
		edges.add(new Vector2(20,20));
		edges.add(new Vector2(0,20));
		World.Polygon polygon2 = new World.Polygon(new Vector2(10,10), edges);
		
		assertTrue(world.isUnoccupied(polygon2));
		
		world.addToWorld(polygon);
		
		
		assertFalse(world.isUnoccupied(polygon2));
		
		
	}
}
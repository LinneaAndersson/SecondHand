package com.secondhand.model.sat;

import java.util.ArrayList;
import java.util.List;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.physics.Vector2;



import junit.framework.TestCase;

public class WorldTest extends TestCase {
	
	public void testIsWithinWorldBounds() {
		
		MyDebug.d("testIsWithinWorldBounds");
		
		
		World world = new World(100, 100);
		
		// for simple squares
		
		List<Vector2> edges = new ArrayList();
		edges.add(new Vector2(-20,-20));
		edges.add(new Vector2(0,-20));
		edges.add(new Vector2(0,0));
		edges.add(new Vector2(-20,0));
		
		Polygon polygon = new Polygon(new Vector2(20,20), edges);
		
		assertTrue(world.isUnoccupied(polygon));
		
		List<Vector2> edges2 = new ArrayList<Vector2>();
		edges2.add(new Vector2(0,0));
		edges2.add(new Vector2(20,0));
		edges2.add(new Vector2(20,20));
		edges2.add(new Vector2(0,20));
		Polygon polygon2 = new Polygon(new Vector2(10,10), edges2);
		
		assertTrue(world.isUnoccupied(polygon2));
		
		world.addToWorld(polygon);
		
		
		assertFalse(world.isUnoccupied(polygon2));
		
		Polygon polygon3 = new Polygon(new Vector2(30,10), edges2);
		assertTrue(world.isUnoccupied(polygon3));
		
	}
	
	public void testIsWithinWorldBounds2() {
		
		MyDebug.d("testIsWithinWorldBounds2");
		
		
		World world = new World(100, 100);
		
		// now do it for polygons
		
		List<Vector2> edges = new ArrayList();
		edges.add(new Vector2(0, 0));
		edges.add(new Vector2(3,1));
		edges.add(new Vector2(4,3));
		edges.add(new Vector2(3,4));
		edges.add(new Vector2(0,3));
		
		Polygon polygon = new Polygon(new Vector2(0,0), edges);
		
		assertTrue(world.isUnoccupied(polygon));
		
		List<Vector2> edges2 = new ArrayList();
		edges2.add(new Vector2(0,1));
		edges2.add(new Vector2(2,0));
		edges2.add(new Vector2(2,1));
		Polygon polygon2 = new Polygon(new Vector2(3,2), edges2);
		
		assertTrue(world.isUnoccupied(polygon2));
		
		world.addToWorld(polygon);
		
		assertFalse(world.isUnoccupied(polygon2));
	}
	
	public void testCreateRectangle() {
		
		MyDebug.d("testCreateRectangle");
		
		World world = new World(100, 100);
		
		Polygon rect1 = PolygonFactory.createRectangle(new Vector2(0,0), 10, 10);
		world.addToWorld(rect1);
		
		Polygon rect2 = PolygonFactory.createRectangle(new Vector2(5,5), 10, 10);
		assertFalse(world.isUnoccupied(rect2));
		
		Polygon rect3 = PolygonFactory.createRectangle(new Vector2(11, 11), 10, 10);
		assertTrue(world.isUnoccupied(rect3));
		
	}
	
	public void testCreateCircle() {
		
		MyDebug.d("testCreateCircle");
	
	World world = new World(100, 100);
		
		Circle rect1 = new Circle(new Vector2(1,1), 1);
		world.addToWorld(rect1);
		
		Circle rect2 = new Circle(new Vector2(1.5f,1), 1);
		assertFalse(world.isUnoccupied(rect2));
		
		Circle rect3 = new Circle(new Vector2(3.5f, 1), 1);
		assertTrue(world.isUnoccupied(rect3));
		
	}

	// with a circle and a polygon. 
	public void testCreateCircle2() {
		
		MyDebug.d("testCreateCircle2");

		World world = new World(100, 100);

		List<Vector2> edges = new ArrayList();
		edges.add(new Vector2(0, 0));
		edges.add(new Vector2(3,1));
		edges.add(new Vector2(4,3));
		edges.add(new Vector2(3,4));
		edges.add(new Vector2(0,3));
		Polygon polygon = new Polygon(new Vector2(0,0), edges);
		
		world.addToWorld(polygon);
	
		
		Circle rect2 =new Circle(new Vector2(6,3), 1);
		assertTrue(world.isUnoccupied(rect2));

		Circle rect3 =new Circle(new Vector2(4, 3), 1);
		assertFalse(world.isUnoccupied(rect3));
	}

	
}
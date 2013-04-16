package com.secondhand.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import junit.framework.TestCase;

public class TriangulatorTest extends TestCase {
	
	public void setUp() {
	}
	
	private static class VectorComparator implements Comparator<Vector2> {
		public int compare(Vector2 v1, Vector2 v2) {
			if(v1.x != v2.x) {
				return (int)(v1.x - v2.x);
			} else {
				return  (int)(v1.y - v2.y);
			}
		}
	}
	
	// unit testing the unit tests :)
	public void testVectorComparator() {
		VectorComparator comp = new VectorComparator();
		Vector2 v1 = new Vector2(2,4);
		
		assertTrue(comp.compare(v1, v1) == 0);
		
		Vector2 v2 = new Vector2(2,1);
		
		assertTrue(comp.compare(v1, v2) > 0);
		assertTrue(comp.compare(v2, v1) < 0);
	}
	
	// doesn't respect order
	public void assertCollectionsEquals(List<Vector2> l1, List<Vector2> l2) {
		// to avoid messing with the order of the original lists.
		l1 = new ArrayList<Vector2>(l1);
		l2 = new ArrayList<Vector2>(l2);
			
		Collections.sort(l1, new VectorComparator());
		Collections.sort(l2, new VectorComparator());
		assertEquals(l1, l2);
	}
	
	public void testTriangulateInvalidPolygon() {
		try {
			List<Vector2> polygon = new ArrayList<Vector2>();
			
			// some points in a straight line is not a valid polygon.
			polygon.add(new Vector2(0,0));
			polygon.add(new Vector2(1,1));
			polygon.add(new Vector2(1,1));
			polygon.add(new Vector2(2,2));
			
			Triangulator.Triangulate(polygon);
			
			fail("Expected Exception IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	public void testTriangulateInvalid1PointPolygon() {
		try {
			List<Vector2> polygon = new ArrayList<Vector2>();
			
			// some points in a straight line is not a valid polygon.
			polygon.add(new Vector2(0,0));

			Triangulator.Triangulate(polygon);
			
			fail("Expected Exception IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	public void testTriangulateInvalid2PointsPolygon() {
		try {
			List<Vector2> polygon = new ArrayList<Vector2>();
			
			// some points in a straight line is not a valid polygon.
			polygon.add(new Vector2(0,0));
			polygon.add(new Vector2(1,1));

			Triangulator.Triangulate(polygon);
			
			fail("Expected Exception IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	public void testTriangulate() {
		// triangle.
		List<Vector2> polygon = new ArrayList<Vector2>();
		
		polygon.add(new Vector2(0,0));
		polygon.add(new Vector2(1,3));
		polygon.add(new Vector2(5,4));
		
		List<Vector2> triangulated = Triangulator.Triangulate(polygon);
			
		assertCollectionsEquals(polygon, triangulated);
		
		// rectangle.
		polygon = new ArrayList<Vector2>();
		
		polygon.add(new Vector2(0,0));
		polygon.add(new Vector2(1,0));
		polygon.add(new Vector2(1,1));
		polygon.add(new Vector2(0,1));
		
		triangulated = Triangulator.Triangulate(polygon);
			
		// a rectangle can be divided into 2 triangles.
		assertEquals(2 * 3, triangulated.size());
		
		// now one with 8 edges, we probably will not be needing more complex polygons than that.
		polygon = new ArrayList<Vector2>();
		
		polygon.add(new Vector2(0,0));
		polygon.add(new Vector2(4,2));
		polygon.add(new Vector2(3,5));
		
		polygon.add(new Vector2(-1, 6));
		polygon.add(new Vector2(-4, 4));
		polygon.add(new Vector2(-3, 2));
		polygon.add(new Vector2(-3, -1));
		polygon.add(new Vector2(-1, -1));
		
		triangulated = Triangulator.Triangulate(polygon);
		
		assertEquals(6 * 3, triangulated.size());
		
	}
	
	/* We don't need to test the method area(), since that method is used as part of the computation 
	 * of triangulate(). If triangulate() works, then area will as well. */
}
package com.secondhand.math;

import java.util.ArrayList;
import java.util.List;


import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.util.PolygonUtil;


import junit.framework.TestCase;

public class PolygonUtilTest extends TestCase {
	
	public void setUp() {
	}
	
	public void testIsConvexInvalid1PointPolygon() {
		try {
			List<Vector2> polygon = new ArrayList<Vector2>();
			
			// some points in a straight line is not a valid polygon.
			polygon.add(new Vector2(0,0));

			PolygonUtil.isConvex(polygon);
			
			fail("Expected Exception IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	public void testIsConvexInvalid2PointsPolygon() {
		try {
			List<Vector2> polygon = new ArrayList<Vector2>();
			
			// some points in a straight line is not a valid polygon.
			polygon.add(new Vector2(0,0));
			polygon.add(new Vector2(1,1));

			PolygonUtil.isConvex(polygon);
			
			fail("Expected Exception IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	public void testIsConvex() {
		// a convex pentagon.
		List<Vector2> polygon = new ArrayList<Vector2>();
		
		polygon.add(new Vector2(2,0));
		polygon.add(new Vector2(3,3));
		polygon.add(new Vector2(0,5));
		polygon.add(new Vector2(-3,3));
		polygon.add(new Vector2(-2,0));

		assertTrue(PolygonUtil.isConvex(polygon));
	
		// now we'll make it concave:
		polygon.get(2).y = 2;
		assertFalse(PolygonUtil.isConvex(polygon));
			
	}
}
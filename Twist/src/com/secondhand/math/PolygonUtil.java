	package com.secondhand.math;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class PolygonUtil {

	private PolygonUtil() {}	
	
	/**
	 * See wikipedia for the definition of a convex polygon.
	 * @param polygon
	 * @return whether the poly is convex.
	 */
	public static boolean  isConvex(List<Vector2> polygon) {
		
		if ( polygon.size() < 3 ) 
			throw new IllegalArgumentException("A polygon with less than 3 points is not a polygon!");

		
		boolean firstPairChecked = false;
		boolean ensureAllPositive = false;
		boolean ensureAllNegative = false;
		
		// go through every consequtive pair of edges. 
		for(int i = 0; i < polygon.size(); ++i) {
			// these 2 edges are defined by these 3 points:
			final Vector2 p1 = polygon.get(i);
			final Vector2 p2 = polygon.get((i + 1) % polygon.size());
			final Vector2 p3 = polygon.get((i + 2) % polygon.size());
			
			// this vector represents the first edge
			final float dx1 = p2.x - p1.x;
			final float dy1 = p2.y - p1.y;
			
			// this vector represents the second edge
			final float dx2 = p3.x - p2.x;
			final float dy2 = p3.y - p2.y;
			
			//
			final float zcrossproduct = dx1*dy2 - dy1*dx2;
						
			/* from the definition of the cross product(if we set the z-component of 
			 * both vectors to 0), we find that the given polygon is convex only if
			 * all the computed z-components(zcrossproduct) are all positive or all negative.
			 * The proof of this is trivial and left as an excecise to the reader :-)
			 */
			if(!firstPairChecked) {
				
				if(zcrossproduct >= 0) {
					ensureAllPositive = true;
				} else {
					ensureAllNegative = true;
				}
				
				firstPairChecked = true;
			} else {
				if(ensureAllPositive && zcrossproduct < 0) {
					return false;
				} else if(ensureAllNegative && zcrossproduct >= 0) {
					return false;
				}
			}
			
		}

		// now we've checked all the adjacent pairs of edges, so it must be convex.
		return true;
	}
}

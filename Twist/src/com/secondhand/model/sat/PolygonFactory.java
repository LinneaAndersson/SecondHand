package com.secondhand.model.sat;

import java.util.ArrayList;
import java.util.List;

import com.secondhand.model.Vector2;


public final class PolygonFactory {

	private PolygonFactory() {}
	
	public static Polygon createRectangle(final Vector2 position, final int width, final int height) {
		final List<Vector2> edges = new ArrayList<Vector2>();
		
		edges.add(new Vector2(0,0));
		edges.add(new Vector2(width,0));
		edges.add(new Vector2(width,height));
		edges.add(new Vector2(0,height));
		
		return new Polygon(position, edges);
	}
	
	private static final float PI = (float) Math.PI;
	private static final float DEG_TO_RAD = PI / 180.0f;
	
	private static final float degToRad(final float pDegree) {
		return DEG_TO_RAD * pDegree;
	}
	
	public static Polygon createCircle(final Vector2 center,  final float radius) {
	
		final List<Vector2> edges = new ArrayList<Vector2>();
		
	    for (float i = 0; i < 360.0f; i += (360.0f / 20)) {
	    		
	    	// middle point vertex
	    	// the right most
	    	// the left most one.

	    	final float x = (float) (center.x + Math.cos(degToRad(360 - i)) * radius);
	    	final float y = (float) (center.y + Math.sin(degToRad(360 - i)) * radius);
	    	edges.add(new Vector2(x,y));
	    }	
	    
		return new Polygon(new Vector2(0,0), edges);
	}
	
}

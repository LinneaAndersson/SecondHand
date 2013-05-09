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
	
	
}

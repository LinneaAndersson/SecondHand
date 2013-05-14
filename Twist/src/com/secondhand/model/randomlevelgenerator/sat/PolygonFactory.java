package com.secondhand.model.randomlevelgenerator.sat;

import java.util.ArrayList;
import java.util.List;

import com.secondhand.model.physics.Vector2;


public final class PolygonFactory {

	private PolygonFactory() {}
	
	public static Polygon createRectangle(final Vector2 position, final float width, final float height) {
		final List<Vector2> edges = new ArrayList<Vector2>();
		
		edges.add(new Vector2(0,0));
		edges.add(new Vector2(width,0));
		edges.add(new Vector2(width,height));
		edges.add(new Vector2(0,height));
		
		return new Polygon(position, edges);
	}
	
	
}

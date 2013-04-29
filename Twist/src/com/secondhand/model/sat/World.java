package com.secondhand.model.sat;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

// Uses the separating axis theorem to implement collision checking.
// See: http://www.codezealot.org/archives/55
// used to generate random level.
public class World {
	
	private final int width;
	private final int height;
	
	// the shapes in the world.
	private List<World.Polygon> polygons;
	
	public World(final int width, final int height) {
		this.width = width;
		this.height = height;
		polygons = new ArrayList<World.Polygon>();
	}
	
	public void addToWorld(World.Polygon polygon) {
		polygons.add(polygon);
	}
	
	private boolean isWithinWorldBounds(final Vector2 position) {
		return position.x >= 0 && position.x <= width &&
				position.y >= 0 && position.y <= height;
	}
	
	public boolean isUnoccupied(World.Polygon polygon) {
		for(Vector2 edge: polygon.edges) {
			if(!isWithinWorldBounds(edge))
				return false;
		}
		
		return true;
	}
	
	public static class Polygon {
		public List<Vector2> edges;
		
		Polygon(final Vector2 position, final List<Vector2> edges) {
			this.edges = new ArrayList<Vector2>();
			for(Vector2 edge: edges) {
				this.edges.add(edge.add(position));
			}
		}
	}
}

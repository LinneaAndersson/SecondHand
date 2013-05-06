package com.secondhand.model.sat;

import java.util.ArrayList;
import java.util.List;

import com.secondhand.model.Vector2;


// Uses the separating axis theorem to implement collision checking.
// Credits: http://www.codezealot.org/archives/55
// used to generate random level.
public class World {

	private final int width;
	private final int height;

	// the shapes in the world.
	private final List<World.Polygon> polygons;

	public World(final int width, final int height) {
		this.width = width;
		this.height = height;
		polygons = new ArrayList<World.Polygon>();
	}

	//Returns true if the 
	public boolean addToWorld(final World.Polygon polygon) {
		if (isUnoccupied(polygon)) {
			polygons.add(polygon);
			return true;
		}
		return false;
	}

	private boolean isWithinWorldBounds(final Vector2 position) {
		return position.x >= 0 && position.x <= width &&
				position.y >= 0 && position.y <= height;
	}

	public boolean isUnoccupied(final World.Polygon polygon) {
		for(final Vector2 edge: polygon.edges) {
			if(!isWithinWorldBounds(edge))
				return false;
		}

		return noIntersection(polygon);

	}

	// implements the separating axes theorem.
	private boolean sat(final World.Polygon poly1, final World.Polygon poly2) {
		final Vector2[] axes1 = poly1.getAxes();
		final Vector2[] axes2 = poly2.getAxes();
		// loop over the axes1
		for (int i = 0; i < axes1.length; i++) {
			final Vector2 axis = new Vector2(axes1[i]);
			// project both shapes onto the axis
			final  Projection p1 = poly1.project(axis);
			final  Projection p2 = poly2.project(axis);
			// do the projections overlap?
			if (!p1.overlap(p2)) {
				// then we can guarantee that the shapes do not overlap
				return false;
			}
		}
		// loop over the axes2
		for (int i = 0; i < axes2.length; i++) {
			final Vector2 axis = new Vector2(axes2[i]);
			// project both shapes onto the axis
			final  Projection p1 = poly1.project(axis);
			final  Projection p2 = poly2.project(axis);
			// do the projections overlap?
			if (!p1.overlap(p2)) {
				// then we can guarantee that the shapes do not overlap
				return false;
			}
		}
		// if we get here then we know that every axis had overlap on it
		// so we can guarantee an intersection
		return true;


	}

	private boolean noIntersection(final World.Polygon polygon) {
		for(final World.Polygon otherPolygon : polygons) {
			if(sat(polygon, otherPolygon)) {
				return false;
			}
		}

		return true;
	}

	
	public static class Polygon {
		public List<Vector2> edges;

		public Polygon(final Vector2 position, final List<Vector2> edges) {
			this.edges = new ArrayList<Vector2>();
			for(final Vector2 edge: edges) {
				this.edges.add(new Vector2(edge.x + position.x, edge.y + position.y));
			}
		}

		public Vector2[] getAxes() {
			Vector2[] axes = new Vector2[edges.size()];
			// loop over the vertices
			for (int i = 0; i <  edges.size(); i++) {
				// get the current vertex
				final Vector2 p1 = new Vector2(edges.get(i));
				// get the next vertex
				final Vector2 p2 = new Vector2(edges.get(i + 1 == edges.size() ? 0 : i + 1));
				// subtract the two to get the edge vector
				final Vector2 edge = p1.sub(p2);
				// get either perpendicular vector
				final  Vector2 normal = new Vector2(-edge.y, edge.x);

				// the perp method is just (x, y) => (-y, x) or (y, -x)
				axes[i] = normal;
			}
			return axes;
		}

		public Projection project(final Vector2 axis) {
			float min = axis.dot(edges.get(0));
			float max = min;
			for (int i = 1; i < edges.size(); i++) {
				// NOTE: the axis must be normalized to get accurate projections
				final float p = axis.dot(edges.get(i));
				if (p < min) {
					min = p;
				} else if (p > max) {
					max = p;
				}
			}
			return new Projection(min, max);
		}
	}

	private static class Projection {

		private final float min;
		private final  float max;

		public Projection(final float min, final float max) {
			this.min = min;
			this.max = max;
		}

		public boolean overlap(final Projection other) {

			return (!(other.max < this.min || this.max < other.min));  
		}	
	}


}

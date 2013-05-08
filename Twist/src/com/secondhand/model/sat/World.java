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
	private final List<Polygon> polygons;

	public World(final int width, final int height) {
		this.width = width;
		this.height = height;
		polygons = new ArrayList<Polygon>();
	}

	//Returns true if the 
	public boolean addToWorld(final Polygon polygon) {
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

	public boolean isUnoccupied(final Polygon polygon) {
		for(final Vector2 edge: polygon.edges) {
			if(!isWithinWorldBounds(edge))
				return false;
		}

		return noIntersection(polygon);

	}

	// implements the separating axes theorem.
	private boolean sat(final Polygon poly1, final Polygon poly2) {
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

	private boolean noIntersection(final Polygon polygon) {
		for(final Polygon otherPolygon : polygons) {
			if(sat(polygon, otherPolygon)) {
				return false;
			}
		}

		return true;
	}


}

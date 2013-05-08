package com.secondhand.model.sat;

import java.util.ArrayList;
import java.util.List;

import com.secondhand.model.Vector2;

public class Polygon {
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
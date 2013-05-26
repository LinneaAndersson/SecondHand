package com.secondhand.model.util.sat;

import java.util.ArrayList;
import java.util.List;

import com.secondhand.model.physics.Vector2;

// CONVEX, polygon class.
public class Polygon extends Shape {
	public List<Vector2> edges;

	public Polygon(final Vector2 position, final List<Vector2> edges) {
		super();
		this.edges = new ArrayList<Vector2>();
		for (final Vector2 edge : edges) {
			this.edges
					.add(new Vector2(edge.x + position.x, edge.y + position.y));
		}
	}

	public Vector2[] getAxes() {
		Vector2[] axes = new Vector2[edges.size()];
		// loop over the vertices
		for (int i = 0; i < edges.size(); i++) {
			// get the current vertex
			final Vector2 p1 = new Vector2(edges.get(i));
			// get the next vertex
			final Vector2 p2 = new Vector2(edges.get(i + 1 == edges.size() ? 0
					: i + 1));
			// subtract the two to get the edge vector
			final Vector2 edge = p1.sub(p2);
			// get either perpendicular vector
			final Vector2 normal = new Vector2(-edge.y, edge.x);

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

	public boolean contains(final Vector2 point) {

		// credits:
		// http://stackoverflow.com/questions/1119627/how-to-test-if-a-point-is-inside-of-a-convex-polygon-in-2d-integer-coordinates

		float sign = 0;

		final int n_vertices = this.edges.size();

		for (int n = 0; n < n_vertices; ++n) {
			final Vector2 segment1 = edges.get(n);

			final Vector2 segment2 = edges.get((n + 1) % n_vertices);
			final Vector2 affine_segment = new Vector2(segment2.x - segment1.x,
					segment2.y - segment1.y);
			final Vector2 affine_point = new Vector2(point.x - segment1.x,
					point.y - segment1.y);

			float k = affine_segment.cross(affine_point);
			k = (int) (k / Math.abs(k)); // normalized to 1 or -1

			if (sign == 0)
				sign = k;
			else if (k != sign)
				return false;
		}
		return true;
	}

	// test whether this polygon contains another polygon.
	public boolean contains(final Polygon otherPolygon) {
		for (final Vector2 point : otherPolygon.edges) {
			if (!this.contains(point))
				return false;
		}

		return true;
	}

	public boolean contains(final Circle circle) {
		return this.contains(circle.position);
	}
}
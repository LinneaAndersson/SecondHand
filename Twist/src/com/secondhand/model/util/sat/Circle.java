package com.secondhand.model.util.sat;

import com.secondhand.model.physics.Vector2;

public class Circle extends Shape {
	
	public Vector2 position;
	public float radius;
	
	public Circle(final Vector2 position,  final float radius) {
		super();
		this.position = position;
		this.radius = radius;
	}
	
	// Source for the circle and line intersection code:
	// http://doswa.com/2009/07/13/circle-segment-intersectioncollision.html
	
	  public Vector2 closest_point_on_seg(final Vector2 seg_a, final Vector2 seg_b) { // NOPMD
	        final Vector2 seg_v = new Vector2(seg_b.x - seg_a.x, seg_b.y - seg_a.y);
	        final Vector2 pt_v = new Vector2(this.position.x - seg_a.x, this.position.y - seg_a.y);
	   
	        final Vector2 seg_v_unit = new Vector2( seg_v.x / seg_v.len(), seg_v.y / seg_v.len());
	        final float proj = pt_v.dot(seg_v_unit);
	        if (proj <= 0)
	            return seg_a;
	        if (proj >= seg_v.len())
	            return seg_b;
	        final Vector2 proj_v = new Vector2(seg_v_unit.x * proj,seg_v_unit.y * proj);
	        return new Vector2(proj_v.x + seg_a.x, proj_v.y + seg_a.y); 
	  }
	
	public boolean intersects(final Vector2 seg_a, final Vector2 seg_b) {
        final Vector2 closest = closest_point_on_seg(seg_a, seg_b);
        final Vector2 dist_v = new Vector2(this.position.x - closest.x, this.position.y - closest.y);
        return dist_v.len() < this.radius;
	}
}

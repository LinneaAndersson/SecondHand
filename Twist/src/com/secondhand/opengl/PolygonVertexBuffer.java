package com.secondhand.opengl;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.opengl.util.FastFloatBuffer;
import org.anddev.andengine.opengl.vertex.VertexBuffer;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.math.Triangulator;


public class PolygonVertexBuffer extends VertexBuffer {
	
	
	// ===========================================================
	// Constants
	// ===========================================================

		
	// ===========================================================
	// Fields
	// ===========================================================
	
	private List<Vector2> mVertices;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public List<Vector2> getVertices() {
		return this.mVertices;
	}
	
	private static int computeVertexCount(int edges) {
		//for the simple polygons we're dealing with,
		// every polygon with n edges can be divided into n-2 triangles. 
		final int triangles = edges - 2;
		
		// every triangles takes 3 vertices.
		return triangles * 3 * 2;
	}

	/**
	 * 
	 * @param edges 
	 * @param pDrawType
	 * @param pManaged
	 */
	public PolygonVertexBuffer(int edges, final int pDrawType, final boolean pManaged) {
		super(computeVertexCount(edges), pDrawType, pManaged);
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	public synchronized void update(final List<Vector2> polygon) {
		
	    final int[] vertices = this.mBufferData;
	    this.mVertices = new ArrayList<Vector2>();

	    // the triangulated polygon
	   List<Vector2> triangles = Triangulator.triangulate(polygon);
	   // List<Vector> triangles = polygon;

	    // put the triangulated polygon in the vertex buffer
	    int vertexI = 0;
	    for(Vector2 v: triangles) {
	    	this.mVertices.add(v);
	    	vertices[vertexI++] = Float.floatToRawIntBits(v.x);
	    	vertices[vertexI++] = Float.floatToRawIntBits(v.y);
	    }

	    final FastFloatBuffer buffer = this.getFloatBuffer();
	    buffer.position(0);
	    buffer.put(vertices);
	    buffer.position(0);

		super.setHardwareBufferNeedsUpdate();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}

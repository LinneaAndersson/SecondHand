package com.secondhand.opengl;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.opengl.util.FastFloatBuffer;
import org.anddev.andengine.opengl.vertex.VertexBuffer;

import com.badlogic.gdx.math.Vector2;


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
	
	private static int computeVertexCount(final int edges) {
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
	public PolygonVertexBuffer(final int edges, final int pDrawType, final boolean pManaged) {
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

	    // put the triangulated polygon in the vertex buffer
	    int vertexI = 0;
	    
	    final float n = polygon.size();
	    
	    final Vector2 v1 = polygon.get(0);
	    
	    // found this method here:
	    // http://www.gamedev.net/topic/603854-finding-the-center-point-for-a-convex-polygon/
	    
	    for(int i = 1; i <= n-2; ++i) {
	    	final Vector2 v2 = polygon.get(i);
	    	final Vector2 v3 = polygon.get(i+1);
	    	
	    	this.mVertices.add(v1);
	    	this.mVertices.add(v3);
	    	this.mVertices.add(v2);
	    	
	    	vertices[vertexI++] = Float.floatToRawIntBits(v1.x);
	    	vertices[vertexI++] = Float.floatToRawIntBits(v1.y);
	    	

	    	vertices[vertexI++] = Float.floatToRawIntBits(v3.x);
	    	vertices[vertexI++] = Float.floatToRawIntBits(v3.y);
	    	
	    	vertices[vertexI++] = Float.floatToRawIntBits(v2.x);
	    	vertices[vertexI++] = Float.floatToRawIntBits(v2.y);
	    	
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

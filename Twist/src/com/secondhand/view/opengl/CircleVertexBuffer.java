package com.secondhand.view.opengl;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.opengl.util.FastFloatBuffer;
import org.anddev.andengine.opengl.vertex.VertexBuffer;
import org.anddev.andengine.util.MathUtils;

import com.secondhand.model.physics.Vector2;


public class CircleVertexBuffer extends VertexBuffer {

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

	private final int mSegments;

	public List<Vector2> getVertices() {
		return this.mVertices;
	}

	public CircleVertexBuffer(final int segments, final int pDrawType,
			final boolean pManaged) {
		super(segments * 2, pDrawType, pManaged);
		this.mSegments = segments;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getSegments() {
		return this.mSegments;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public synchronized void update(final float radius) {

		final int[] vertices = this.mBufferData;
		this.mVertices = new ArrayList<Vector2>();

		int count = 0;
		for (float i = 0; i < 360.0f; i += (360.0f / mSegments)) {

			// middle point vertex
			// the right most
			// the left most one.

			final float x = (float) (Math.cos(MathUtils
					.degToRad(360 - i)) * radius);
			final float y = (float) (Math.sin(MathUtils
					.degToRad(360 - i)) * radius);

			vertices[count++] = Float.floatToRawIntBits(x);
			vertices[count++] = Float.floatToRawIntBits(y);
			this.mVertices.add(new Vector2(x, y));
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

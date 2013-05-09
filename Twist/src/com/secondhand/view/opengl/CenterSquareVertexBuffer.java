package com.secondhand.view.opengl;

import org.anddev.andengine.opengl.util.FastFloatBuffer;
import org.anddev.andengine.opengl.vertex.VertexBuffer;


public class CenterSquareVertexBuffer extends VertexBuffer {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int VERTICES_PER_RECTANGLE = 4;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CenterSquareVertexBuffer(final int pDrawType, final boolean pManaged) {
		super(2 * VERTICES_PER_RECTANGLE, pDrawType, pManaged);
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
	
	public synchronized void update(final float side) {

		final float radius = side / 2f;
		
		final int[] bufferData = this.mBufferData;
		
		bufferData[0] = Float.floatToRawIntBits(+radius);
		bufferData[1] = Float.floatToRawIntBits(-radius);

		bufferData[2] = Float.floatToRawIntBits(+radius);
		bufferData[3] = Float.floatToRawIntBits(+radius);

		bufferData[4] = Float.floatToRawIntBits(-radius);
		bufferData[5] = Float.floatToRawIntBits(+radius);

		bufferData[6] = Float.floatToRawIntBits(-radius);
		bufferData[7] = Float.floatToRawIntBits(-radius);

		final FastFloatBuffer buffer = this.getFloatBuffer();
		buffer.position(0);
		buffer.put(bufferData);
		buffer.position(0);

		super.setHardwareBufferNeedsUpdate();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}

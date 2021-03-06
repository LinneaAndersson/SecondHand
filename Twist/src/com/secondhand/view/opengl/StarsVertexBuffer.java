package com.secondhand.view.opengl;

import java.util.Random;

import org.anddev.andengine.opengl.util.FastFloatBuffer;
import org.anddev.andengine.opengl.vertex.VertexBuffer;

public class StarsVertexBuffer extends VertexBuffer {

	private final int mStars;

	public StarsVertexBuffer(final int stars, final int pDrawType,
			final boolean pManaged) {
		super(stars * 2, pDrawType, pManaged);
		this.mStars = stars;
	}

	public int getStars() {
		return this.mStars;
	}

	public synchronized void update(final float width, final float height) {

		final int[] vertices = this.mBufferData;

		final Random rng = new Random();
		int count = 0;
		for (int i = 0; i < this.getStars(); ++i) {
			final float x = (float) (rng.nextInt(Integer.MAX_VALUE) % (int) width);
			final float y = (float) (rng.nextInt(Integer.MAX_VALUE) % (int) height);

			vertices[count++] = Float.floatToRawIntBits(x);
			vertices[count++] = Float.floatToRawIntBits(y);
		}

		final FastFloatBuffer buffer = this.getFloatBuffer();
		buffer.position(0);
		buffer.put(vertices);
		buffer.position(0);

		super.setHardwareBufferNeedsUpdate();
	}

}

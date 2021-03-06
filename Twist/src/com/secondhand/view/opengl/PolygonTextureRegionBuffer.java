package com.secondhand.view.opengl;

import java.util.List;

import org.anddev.andengine.opengl.buffer.BufferObject;
import org.anddev.andengine.opengl.util.FastFloatBuffer;

import com.secondhand.model.physics.Vector2;

public class PolygonTextureRegionBuffer extends BufferObject {

	protected final float mWidth;
	protected final float mHeight;
	protected final List<Vector2> mVertices;

	public PolygonTextureRegionBuffer(final List<Vector2> vertices,
			final float width, final float height, final int pDrawType,
			final boolean pManaged) {
		super(vertices.size() * 2, pDrawType, pManaged);

		this.mWidth = width;
		this.mHeight = height;
		this.mVertices = vertices;
	}

	public synchronized void update() {
		final int[] bufferData = this.mBufferData;

		int i = 0;
		for (final Vector2 vertex : this.mVertices) {
			bufferData[i++] = Float.floatToRawIntBits(vertex.x / this.mWidth);
			bufferData[i++] = Float.floatToRawIntBits(vertex.y / this.mHeight);
		}

		final FastFloatBuffer buffer = this.mFloatBuffer;
		buffer.position(0);
		buffer.put(bufferData);
		buffer.position(0);

		super.setHardwareBufferNeedsUpdate();
	}

}

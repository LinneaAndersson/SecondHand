package com.secondhand.opengl;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.opengl.vertex.VertexBuffer;

public class StarsBackground extends RectangularShape {
	
	protected final StarsVertexBuffer mStarsVertexBuffer;

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final float mStarsSize;

	// ===========================================================
	// Constructors
	// ===========================================================
	

	public StarsBackground(final int stars, final float starsSize, final float width, final float height) {	
		super(0, 0, width, height, null);
		
		this.mStarsSize = starsSize;
		
		this.mStarsVertexBuffer = new StarsVertexBuffer(stars, GL11.GL_STATIC_DRAW, true);
		this.updateVertexBuffer();
			
		super.mWidth = width;
		super.mBaseWidth = width;
		
		super.mHeight = height;
		super.mBaseHeight = height;
		
		this.mRotationCenterX = width * 0.5f;
		this.mRotationCenterY = height * 0.5f;

		this.mScaleCenterX = this.mRotationCenterX;
		this.mScaleCenterY = this.mRotationCenterY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onInitDraw(final GL10 pGL) {
		super.onInitDraw(pGL);

		// no textures needed.
		GLHelper.disableTextures(pGL);
		GLHelper.disableTexCoordArray(pGL);
	}

	@Override
	protected void drawVertices(final GL10 pGL, final Camera pCamera) {
		
		float zoomFactor;
		
		// handle a zoomed in camera.
		if(pCamera instanceof SmoothCamera) {
			zoomFactor = ((SmoothCamera) pCamera).getZoomFactor();
		} else {
			zoomFactor = 1;
		}
		
		pGL.glPointSize(mStarsSize * zoomFactor);
		pGL.glDrawArrays(GL10.GL_POINTS, 0, this.mStarsVertexBuffer.getStars());
		
		// TOOD: don't render the stars that are not in the camera. 
	}
	
	@Override
	protected void onUpdateVertexBuffer() {
		this.mStarsVertexBuffer.update(this.mWidth, this.mHeight);
	}
	
	@Override
	public VertexBuffer getVertexBuffer() {
		return this.mStarsVertexBuffer;
	}
}

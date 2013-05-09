package com.secondhand.view.opengl;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.opengl.vertex.VertexBuffer;

// a square positioned by it's center coordinate. 
public class CenterSquare extends Shape {

	protected final CenterSquareVertexBuffer vertexBuffer;
	
	protected float side;
	
	private final TextureRegion textureRegion;

	public CenterSquare(final float pX, final float pY, final float side, final TextureRegion textureRegion) {
		super(pX, pY);
		
		this.side = side;
		
		this.textureRegion = textureRegion;

		super.setCullingEnabled(true);

		this.vertexBuffer = new CenterSquareVertexBuffer(GL11.GL_STATIC_DRAW, true);
		this.updateVertexBuffer();

		final float width = side * 2;
		final float height = side * 2;

		this.mRotationCenterX = width * 0.5f;
		this.mRotationCenterY = height * 0.5f;

		this.mScaleCenterX = this.mRotationCenterX;
		this.mScaleCenterY = this.mRotationCenterY;
	}

	public float getSide() {
		return side;
	}
	
	public void setSide(final float side) {
		this.side = side;
		this.updateVertexBuffer();
	}

	@Override
	public boolean collidesWith(final IShape pOtherShape) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(final float pX, final float pY) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void onUpdateVertexBuffer() {
		this.vertexBuffer.update(this.getSide());
	}

	@Override
	protected void onInitDraw(final GL10 pGL) {
		super.onInitDraw(pGL);
	
		GLHelper.enableTextures(pGL);
		GLHelper.enableTexCoordArray(pGL);
	
	}
	
	@Override
	protected boolean isCulled(final Camera pCamera) {
		final float x = this.mX - this.getSide();
		final float y = this.mY - this.getSide();
		return x > pCamera.getMaxX()
			|| y > pCamera.getMaxY()
			|| x + this.getWidth() < pCamera.getMinX()
			|| y + this.getHeight() < pCamera.getMinY();
	}

	@Override
	protected void drawVertices(final GL10 pGL, final Camera pCamera) {
		pGL.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
	}	
	
	@Override
	protected void doDraw(final GL10 pGL, final Camera pCamera) {
		this.textureRegion.onApply(pGL);

		super.doDraw(pGL, pCamera);
	}

	@Override
	public float getWidth() {
		return side;
	}

	@Override
	public float getHeight() {
		return getWidth();
	}

	@Override
	public float getBaseWidth() {
		return this.getWidth();
	}

	@Override
	public float getBaseHeight() {
		return this.getWidth();
	}

	@Override
	protected VertexBuffer getVertexBuffer() {
		return this.vertexBuffer;
	}
}

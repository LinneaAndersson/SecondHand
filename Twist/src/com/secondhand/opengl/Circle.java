package com.secondhand.opengl;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.opengl.vertex.VertexBuffer;

import com.badlogic.gdx.math.Vector2;

/**
 * A circle class used for drawing circles. 
 * @author erkastina
 * IMPORTANT: the center position is used to position circles. 
 *
 */
public class Circle extends Shape{
	
	protected float mRadius;
	
	protected final CircleVertexBuffer mCircleVertexBuffer;
	
	/**
	 * Culling enabled by default. 
	 */
	public Circle(final float pX, final float pY, final float radius) {
		this(pX, pY, radius, true);
	}
	
	
	public Circle(final float pX, final float pY, final float radius, final boolean cullingEnabled) {
		super(pX/* + radius*/, pY/* + radius*/);
		
		super.setCullingEnabled(cullingEnabled);
		
		this.mRadius = radius;

		this.mCircleVertexBuffer = new CircleVertexBuffer(100, GL11.GL_STATIC_DRAW, true);
		this.updateVertexBuffer();

		final float width = mRadius * 2;
		final float height = mRadius * 2;

		this.mRotationCenterX = width * 0.5f;
		this.mRotationCenterY = height * 0.5f;

		this.mScaleCenterX = this.mRotationCenterX;
		this.mScaleCenterY = this.mRotationCenterY;
	}
	
	@Override
	public float getWidth() {
		return this.mRadius * 2;
	}
	
	public void setRadius(final float radius) {
		this.mRadius = radius;
		this.updateVertexBuffer();
	}

	@Override
	public float getHeight() {
		return getWidth();
	}
	
	public float getRadius() {
		return this.mRadius;
	}

	@Override
	public float getBaseWidth() {
		return getWidth();
	}

	@Override
	public float getBaseHeight() {
		return getBaseWidth();
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
		this.mCircleVertexBuffer.update(this.getRadius());
	}
	
	@Override
	protected VertexBuffer getVertexBuffer() {
		return this.mCircleVertexBuffer;
	}

	public List<Vector2> getVertices() {
		return this.mCircleVertexBuffer.getVertices();
	}

	@Override
	protected void onInitDraw(final GL10 pGL) {
		super.onInitDraw(pGL);
		GLHelper.disableTextures(pGL);
		GLHelper.disableTexCoordArray(pGL);
	}

	@Override
	protected void drawVertices(final GL10 pGL, final Camera pCamera) {
		pGL.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, this.mCircleVertexBuffer.getSegments());

	}	

	@Override
	protected boolean isCulled(final Camera pCamera) {
		final float cx = this.mX - this.mRadius;
		final float cy = this.mY - this.mRadius;
		return cx > pCamera.getMaxX()
			|| cy > pCamera.getMaxY()
			|| cx + this.getWidth() < pCamera.getMinX()
			|| cy + this.getHeight() < pCamera.getMinY();
	}
	
	protected void applyRotation(final GL10 pGL) {
		final float rotation = this.mRotation;

		if(rotation != 0) {
			//final float rotationCenterX = this.mRotationCenterX;
			//final float rotationCenterY = this.mRotationCenterY;

			//pGL.glTranslatef(rotationCenterX, rotationCenterY, 0);
			pGL.glRotatef(rotation, 0, 0, 1);
			//pGL.glTranslatef(-rotationCenterX, -rotationCenterY, 0);

			/* TODO There is a special, but very likely case when mRotationCenter and mScaleCenter are the same.
			 * In that case the last glTranslatef of the rotation and the first glTranslatef of the scale is superfluous.
			 * The problem is that applyRotation and applyScale would need to be "merged" in order to efficiently check for that condition.  */
		}
	}

}

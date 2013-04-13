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
import com.secondhand.twirl.MyDebug;

/**
 * A circle class used for drawing circles. 
 * @author erkastina
 *
 */
public class Circle extends Shape{
	
	protected final float mRadius;
	
	protected final CircleVertexBuffer mCircleVertexBuffer;
	
	/**
	 * Culling enabled by default. 
	 */
	public Circle(final float pX, final float pY, final float radius) {
		this(pX, pY, radius, true);
	}
	
	
	/**
	 * Creates a new circle.
	 * @param pX the x position of the left side of the circle
	 * @param pY the y position of the top of the circle.
	 * @param radius the radius of the circle. 
	 */
	public Circle(final float pX, final float pY, final float radius, boolean cullingEnabled) {
		super(pX + radius, pY + radius);
		
		super.setCullingEnabled(cullingEnabled);
		
		this.mRadius = radius;

		this.mCircleVertexBuffer = new CircleVertexBuffer(100, GL11.GL_STATIC_DRAW, true);
		this.updateVertexBuffer();

		final float width = this.getWidth();
		final float height = this.getHeight();

		this.mRotationCenterX = width * 0.5f;
		this.mRotationCenterY = height * 0.5f;

		this.mScaleCenterX = this.mRotationCenterX;
		this.mScaleCenterY = this.mRotationCenterY;
	}
	
	@Override
	public float getWidth() {
		return this.mRadius * 2;
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
	public boolean collidesWith(IShape pOtherShape) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(float pX, float pY) {
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
	protected void drawVertices(GL10 pGL, Camera pCamera) {
		pGL.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, this.mCircleVertexBuffer.getSegments());

	}	

	@Override
	protected boolean isCulled(Camera pCamera) {

//		MyDebug.d("culling circle");

		// center coordinates
		final float cx = this.mX + this.mRadius;
		final float cy = this.mY + this.mRadius;
		
		final float r = this.mRadius;
		
		boolean culled = 
				cx >= pCamera.getMaxX() + r
				|| cy >= pCamera.getMaxY() + r
				|| cx <= pCamera.getMinX() - r
				|| cy <= pCamera.getMinY() - r;
				
				if(culled) {
					MyDebug.d("circle is culled!");
				}	
			
				return culled;
	}

}

package com.secondhand.view.opengl;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.opengl.vertex.VertexBuffer;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.secondhand.model.physics.Vector2;

/*
 * A polygon class used for drawing polygons. The positioning for polygons works
 * as follows: All the points of the polygon(the list polygon in the
 * constructor) are positioned relatively to the position of the polygon(the
 * position (pX, pY) specified in the constructor) So a point point (0,0) in the
 * polygon with position (x,y) will be placed at position (x,y) Likewise, the
 * point (10,11) in that same polygon will be at position (x+10,y+11)
 *
 * @author erkastina
 *
 */
public class Polygon extends Shape {

	protected final PolygonVertexBuffer mPolygonVertexBuffer;

	protected List<Vector2> mPolygon;
	private Body mBody;
	private PolygonShape mPolygonShape;

	public Polygon(final Vector2 position, final List<Vector2> polygon) {
		this(position.x, position.y, polygon);

	}

	/*
	 * Culling enabled by default.
	 */
	public Polygon(final float pX, final float pY, final List<Vector2> polygon) {
		this(pX, pY, polygon, true);
	}

	/*
	 * 
	 * @param polygon the points that define the polygon. e.g., for a square
	 * these points will be its four corners.
	 */
	public Polygon(final float pX, final float pY, final List<Vector2> polygon,
			final boolean cullingEnabled) {

		super(pX, pY);

		this.mPolygon = polygon;

		super.setCullingEnabled(cullingEnabled);

		this.mPolygonVertexBuffer = new PolygonVertexBuffer(
				this.mPolygon.size(), GL11.GL_STATIC_DRAW, true);
		this.updateVertexBuffer();
	}

	public List<Vector2> getPolygon() {
		return this.mPolygon;
	}

	@Override
	public float getWidth() {
		return 0;
	}

	@Override
	public float getHeight() {
		return 0;
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
		this.mPolygonVertexBuffer.update(this.mPolygon);
	}

	@Override
	protected VertexBuffer getVertexBuffer() {
		return this.mPolygonVertexBuffer;
	}

	public List<Vector2> getVertices() {
		return this.mPolygonVertexBuffer.getVertices();
	}

	@Override
	protected void onInitDraw(final GL10 pGL) {
		super.onInitDraw(pGL);
		GLHelper.disableTextures(pGL);
		GLHelper.disableTexCoordArray(pGL);
	}

	@Override
	protected void drawVertices(final GL10 pGL, final Camera pCamera) {
		pGL.glDrawArrays(GL10.GL_TRIANGLES, 0, this.mPolygonVertexBuffer
				.getVertices().size());
	}

	private static boolean isPointInCamera(final Camera camera,
			final Vector2 point) {
		return camera.getMinX() < point.x && camera.getMaxX() > point.x
				&& camera.getMinY() < point.y && camera.getMaxY() > point.y;
	}

	public void setBody(final Body body) {
		this.mBody = body;
		final Fixture fixture = mBody.getFixtureList().get(0);
		this.mPolygonShape = (PolygonShape) fixture.getShape();
	}

	@Override
	protected boolean isCulled(final Camera pCamera) {

		for (int i = 0; i < this.mPolygonShape.getVertexCount(); ++i) {
			final com.badlogic.gdx.math.Vector2 v = new com.badlogic.gdx.math.Vector2(
					0, 0);
			this.mPolygonShape.getVertex(i, v);

			final com.badlogic.gdx.math.Vector2 temp = this.mBody
					.getWorldPoint(v);
			final Vector2 trueV = new Vector2(temp.x, temp.y);

			final Vector2 translatedPoint = new Vector2(
					PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT * trueV.x,
					PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT * trueV.y);

			if (isPointInCamera(pCamera, translatedPoint)) {
				return false;
			}
		}
		return true;

	}

}

package com.secondhand.opengl;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.util.GLHelper;

import com.badlogic.gdx.math.Vector2;


/**
 * This class is used when you want to apply a repeating texture on polygon. 
 *
 */
public class TexturedPolygon extends Polygon {

	private final TextureRegion mTextureRegion;
	
	PolygonTextureRegionBuffer mPolygonTextureRegionBuffer;
	
	public TexturedPolygon(final float pX, final float pY, final List<Vector2> polygon, final TextureRegion textureRegion) {
		super(pX, pY, polygon);
		
		this.mPolygonTextureRegionBuffer = new PolygonTextureRegionBuffer(
				super.getVertices(), 
				
				// should these be the circle sizes or the texture sizes?
				textureRegion.getWidth(), textureRegion.getHeight(),  GL11.GL_STATIC_DRAW, true);
		
		// TODO: maybe we should also update if the polygon is changed?
		this.mPolygonTextureRegionBuffer.update();
		
		this.mTextureRegion = textureRegion;
	}
	
	@Override
	protected void onUpdateVertexBuffer() {
		super.onUpdateVertexBuffer();
	}
	
	public TextureRegion getTextureRegion() {
		return this.mTextureRegion;
	}
	
	@Override
	protected void onInitDraw(final GL10 pGL) {
		super.onInitDraw(pGL);
		GLHelper.enableTextures(pGL);
		GLHelper.enableTexCoordArray(pGL);
	}
	
	public void onApplyTextureRegion(final GL10 pGL) {
		this.mTextureRegion.getTexture().bind(pGL);

		if(GLHelper.EXTENSIONS_VERTEXBUFFEROBJECTS) {
			final GL11 gl11 = (GL11)pGL;

			this.mPolygonTextureRegionBuffer.selectOnHardware(gl11);
			GLHelper.texCoordZeroPointer(gl11);
		} else {
			GLHelper.texCoordPointer(pGL, this.mPolygonTextureRegionBuffer.getFloatBuffer());
		}
	}

	@Override
	protected void doDraw(final GL10 pGL, final Camera pCamera) {
		onApplyTextureRegion(pGL);

		super.doDraw(pGL, pCamera);
	}


	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if(mPolygonTextureRegionBuffer.isManaged()) {
			mPolygonTextureRegionBuffer.unloadFromActiveBufferObjectManager();
		}
	}
}
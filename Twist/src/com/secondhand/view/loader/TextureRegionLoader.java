package com.secondhand.view.loader;

import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.secondhand.debug.MyDebug;

/**
 * Singelton class that makes loading TextureRegions much more convenient. 
 */
public class TextureRegionLoader extends Loader {
	
	private static TextureRegionLoader instance;
	
	public static TextureRegionLoader getInstance() {
		if(instance == null) {
			instance = new TextureRegionLoader();
		}
		return instance;
	}
		
	public TextureRegion loadTextureRegion(final String fileName, final int width, 
			final int height, final TextureOptions textureOptions) {
		
		final BitmapTextureAtlas texture = new BitmapTextureAtlas(width, height, textureOptions);
    	final TextureRegion textureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
    			texture, this.context, fileName, 0, 0);
    	
    	this.engine.getTextureManager().loadTexture(texture);
    	
    	try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			MyDebug.e(e);
		}
    	
    	return textureRegion;
	}

	public TextureRegion loadTextureRegion(final String fileName, final int width, final int height) {
		return loadTextureRegion(fileName, width, height, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	}
	
	public AssetBitmapTextureAtlasSource loadAssetBitmapTextureAtlasSource(final String fileName) {
		return new AssetBitmapTextureAtlasSource(this.context, fileName);
	}
}

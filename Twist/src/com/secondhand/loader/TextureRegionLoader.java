package com.secondhand.loader;

import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

/**
 * Singelton class that makes loading TextureRegions much more convenient. 
 */
public class TextureRegionLoader extends Loader {
	
	private static TextureRegionLoader instance = null;
	
	public static TextureRegionLoader getInstance() {
		if(instance == null) {
			instance = new TextureRegionLoader();
		}
		return instance;
	}
	
	public void setAssetBasePath(final String path) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(path);
	}
	
	public TextureRegion loadTextureRegion(final String fileName, final int width, 
			final int height, final TextureOptions textureOptions) {
		
		BitmapTextureAtlas texture = new BitmapTextureAtlas(width, height, textureOptions);
    	TextureRegion textureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
    			texture, this.context, fileName, 0, 0);
    	
    	this.engine.getTextureManager().loadTexture(texture);
    	
    	return textureRegion;
	}

	public TextureRegion loadTextureRegion(final String fileName, final int width, final int height) {
		return loadTextureRegion(fileName, width, height, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	}
}

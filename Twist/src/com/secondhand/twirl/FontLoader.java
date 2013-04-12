package com.secondhand.twirl;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

import android.graphics.Typeface;

/**
 * Singelton class for simplifying the loading of fonts.
 */
public class FontLoader extends Loader {
	
	private static FontLoader instance = null;
	
	public static FontLoader getInstance() {
		if(instance == null) {
			instance = new FontLoader();
		}
		return instance;
	}

	@Override
	public void setAssetBasePath(final String path) {
		FontFactory.setAssetBasePath(path);

	}

	@Override
	public String getDefaultAssetBasePath() {
		return "fonts/";
	}
	
	public Font loadFont(final Typeface typeface, final float size, final int color) {
		
		BitmapTextureAtlas fontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Font font = FontFactory.create(fontTexture, typeface, size, true, color);
		
		this.engine.getTextureManager().loadTexture(fontTexture);	
		this.engine.getFontManager().loadFont(font);
		
		return font;
	}

}

package com.secondhand.twirl;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.secondhand.loader.FontLoader;
import com.secondhand.loader.TextureRegionLoader;

import android.graphics.Color;
import android.graphics.Typeface;

/**
 * Singelton class for managing the global resources of the app.
 * These are resources that are used in several scenes, ie, the menu font. 
 */
public class GlobalResources {
	
	public Font menuItemFont;
	public Font menuHeadlineFont;
	
	public TextureRegion playerSprite;
	public TextureRegion planetTexture;
	
	private static GlobalResources instance = null;
	
	public static GlobalResources getInstance() {
		if(instance == null) {
			instance = new GlobalResources();
		}
		return instance;
	}
	
	private GlobalResources() {}

	public void load() {	
		this.menuItemFont = FontLoader.getInstance().loadFont(
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, Color.WHITE);
		this.menuHeadlineFont = FontLoader.getInstance().loadFont(
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 50, Color.WHITE);
		this.playerSprite = null; // TODO load the player sprite
		this.planetTexture = TextureRegionLoader.getInstance().loadTextureRegion("gfx/planet.png", 32, 32,
				TextureOptions.REPEATING_NEAREST);
	}
	
}

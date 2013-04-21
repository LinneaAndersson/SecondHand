package com.secondhand.twirl;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.secondhand.loader.FontLoader;
import com.secondhand.loader.SoundLoader;
import com.secondhand.loader.TextureRegionLoader;

import android.graphics.Color;
import android.graphics.Typeface;

/**
 * Singelton class for managing the global resources of the app.
 * These are resources that are used in several scenes, ie, the menu font. 
 */
public final class GlobalResources {
	
	private static final String BASEPATH = "gfx/";
	
	public Font menuItemFont;
	public Font menuHeadlineFont;
	
	public TextureRegion playerSprite;
	public TextureRegion planetTexture;
	
	public Sound powerUpSound;
	public Sound growSound;
	public Sound obstacleCollisionSound;
	
	private static GlobalResources instance;
	
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
		//this.playerSprite = null; // TODO load the player sprite
		

		this.planetTexture = TextureRegionLoader.getInstance().loadTextureRegion(BASEPATH+EntityTexture.PLANET.path, 256, 256,
				TextureOptions.REPEATING_BILINEAR);
		
		this.powerUpSound = SoundLoader.getInstance().loadSound("sfx/powerup.wav");
		this.growSound = SoundLoader.getInstance().loadSound("sfx/grow.wav");
		this.obstacleCollisionSound = SoundLoader.getInstance().loadSound("sfx/obstacle_collision.wav");
	}
	
	
	
	
	
	// To add a texture path, just add enum value ex: ENEMY ("enemy.png") in list.
	enum EntityTexture {
		PLANET ("planet_red.png"),
		PLAYER ("player.png");
		
		private String path;
		
		private EntityTexture(final String path) {
			this.path = path;
		}
	}
}

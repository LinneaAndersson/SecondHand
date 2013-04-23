package com.secondhand.twirl;


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
	
	private static final String TEXTURE_BASEPATH = "gfx/";
	private static final String SOUND_BASEPATH = "sfx/";
	
	public TextureRegion playerSprite;
	public TextureRegion planetTexture;
	public TextureRegion powerUpTexture;
	public TextureRegion obstacleTexture;
	
	
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
		//this.playerSprite = null; // TODO load the player sprite
		

		this.planetTexture = TextureRegionLoader.getInstance().loadTextureRegion(TEXTURE_BASEPATH+EntityTexture.PLANET.path, 256, 256,
			TextureOptions.REPEATING_BILINEAR);

		this.obstacleTexture = TextureRegionLoader.getInstance().loadTextureRegion(TEXTURE_BASEPATH+EntityTexture.OBSTACLE.path, 256, 256,
				TextureOptions.REPEATING_BILINEAR);

		
		this.powerUpTexture = TextureRegionLoader.getInstance().loadTextureRegion(TEXTURE_BASEPATH+EntityTexture.POWER_UP.path, 64, 64,
				TextureOptions.REPEATING_BILINEAR);
		
		this.powerUpSound = SoundLoader.getInstance().loadSound(SOUND_BASEPATH+"powerup.wav");
		this.growSound = SoundLoader.getInstance().loadSound(SOUND_BASEPATH+"grow.wav");
		this.obstacleCollisionSound = SoundLoader.getInstance().loadSound(SOUND_BASEPATH+"obstacle_collision.wav");
	}

	
	// To add a texture path, just add enum value ex: ENEMY ("enemy.png") in list.
	enum EntityTexture {
		PLANET ("planet_blood.png"),
		PLAYER ("player.png"),
		POWER_UP("powerup_box.png"),
		OBSTACLE("obstacle.png");
		
		private String path; // NOPMD
		
		private EntityTexture(final String path) {
			this.path = path;
		}
	}
}

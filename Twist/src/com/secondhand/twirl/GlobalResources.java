package com.secondhand.twirl;


import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.secondhand.loader.TextureRegionLoader;


/**
 * Singelton class for managing the global resources of the app.
 * These are resources that are used in several scenes, ie, the menu font. 
 */
public final class GlobalResources {
	
	private static final String TEXTURE_BASEPATH = "gfx/";
	
	public TextureRegion playerSprite;
	public TextureRegion planetTexture;
	public TextureRegion powerUpTexture;
	public TextureRegion obstacleTexture;
	
	
	
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

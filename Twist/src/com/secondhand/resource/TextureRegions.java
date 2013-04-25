package com.secondhand.resource;

import java.util.EnumMap;
import java.util.Map;

import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.secondhand.loader.TextureRegionLoader;


public class TextureRegions {

	private static final String TEXTURE_BASEPATH = "gfx/";
	

	public TextureRegion playerSprite;
	public TextureRegion obstacleTexture;
	public Map<PlanetType, TextureRegion> planetTextures;
	
	public Map<PowerUpType, TextureRegion> powerUpTextures;
	
	
	private static TextureRegions instance;
	
	public static TextureRegions getInstance() {
		if(instance == null) {
			instance = new TextureRegions();
		}
		return instance;
	}
	
	public TextureRegion getPlanetTexture(final PlanetType planetType) {
		return planetTextures.get(planetType);
	}
	
	public TextureRegion getPowerUpTexture(final PowerUpType powerUpType) {
		return powerUpTextures.get(powerUpType);
	}
	
	public void load() {	
		//this.playerSprite = null; // TODO load the player sprite

		planetTextures = new EnumMap<PlanetType, TextureRegion>(PlanetType.class);
		
		for (final PlanetType planetType : PlanetType.values()) {
			final TextureRegion planetTexture = TextureRegionLoader.getInstance().loadTextureRegion(TEXTURE_BASEPATH+planetType.getPath(), 256, 256,
					TextureOptions.REPEATING_BILINEAR);
			planetTextures.put(planetType, planetTexture);
		}
		
		powerUpTextures = new EnumMap<PowerUpType, TextureRegion>(PowerUpType.class);
		
		for (final PowerUpType powerUpType : PowerUpType.values()) {
			final TextureRegion planetTexture = TextureRegionLoader.getInstance().loadTextureRegion(TEXTURE_BASEPATH+powerUpType.getPath(), 64, 64,
					TextureOptions.REPEATING_BILINEAR);
			powerUpTextures.put(powerUpType, planetTexture);
		}
		
		
		this.obstacleTexture = TextureRegionLoader.getInstance().loadTextureRegion(TEXTURE_BASEPATH+EntityTexture.OBSTACLE.path, 256, 256,
				TextureOptions.REPEATING_BILINEAR);
	}

	
	// To add a texture path, just add enum value ex: ENEMY ("enemy.png") in list.
	enum EntityTexture {
		POWER_UP("powerup_box.png"),
		OBSTACLE("obstacle.png");
		
		private String path; // NOPMD
		
		private EntityTexture(final String path) {
			this.path = path;
		}
	}
	
}

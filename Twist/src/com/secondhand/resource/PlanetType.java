package com.secondhand.resource;

public enum PlanetType {
		BLOOD("planet_blood.png", 256, 256),
		GRAY("planet_gray.png", 256, 256),
		PURPLE("planet_purple.png", 256, 256),
		RED("planet_red.png", 256, 256),
		WTF("planet_wtf.png", 256, 256),
		LIFE("planet_life.png", 256, 256),
		DRUGS("planet_drugs.png", 256, 256),
		GRAMLICH("gramlich.jpg", 128, 128),
		WOOD("wood_planet.png", 256, 256),
		DEATH("planet_death.png", 256, 256),
		JEWELS("planet_jewels.png", 256, 256);
		
		private String path; 
		private int width;
		private int height;
		
		private PlanetType(final String path, final int width, final int height) {
			this.path = path;
			this.width = width;
			this.height = height;
		}
		
		public String getPath() {
			return this.path;
		}
		
		public int getWidth() {
			return this.width;
		}
		
		public int getHeight() {
			return this.height;
		}
}


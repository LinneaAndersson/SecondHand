package com.secondhand.resource;




public enum PlanetType {
		BLOOD("planet_blood.png"),
		GRAY("planet_gray.png"),
		PURPLE("planet_purple.png"),
		RED("planet_red.png"),
		WTF("planet_wtf.png");
		
		private String path; 
		
		private PlanetType(final String path) {
			this.path = path;
		}
		
		public String getPath() {
			return this.path;
		}
	}


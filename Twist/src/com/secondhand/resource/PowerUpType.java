package com.secondhand.resource;




public enum PowerUpType {
		BLOOD("eat_obstacle_power_up.png");
		
		private String path; 
		
		private PowerUpType(final String path) {
			this.path = path;
		}
		
		public String getPath() {
			return this.path;
		}
}


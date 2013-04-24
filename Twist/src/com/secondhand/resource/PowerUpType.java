package com.secondhand.resource;


public enum PowerUpType {
		EAT_OBSTACLE("eat_obstacle_power_up.png"),
		SHIELD("shield_power_up.png"),
		RANDOM_TELEPORT("random_teleport_power_up.png");
		
		private String path; 
		
		private PowerUpType(final String path) {
			this.path = path;
		}
		
		public String getPath() {
			return this.path;
		}
}


package com.secondhand.model;


public enum PowerUpType {
		EAT_OBSTACLE("eat_obstacle_power_up.png"),
		SHIELD("shield_power_up.png"),
		RANDOM_TELEPORT("random_teleport_power_up.png"),
		SCORE_UP("score_up_power_up.png"),
		SPEED_UP("speed_up_power_up.png"),
		EXTRA_LIFE("extra_life_power_up.jpg");
		
		private String path; 
		
		private PowerUpType(final String path) {
			this.path = path;
		}
		
		public String getPath() {
			return this.path;
		}
}
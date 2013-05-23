package com.secondhand.model.resource;


public enum PowerUpType {
		DOUBLE_SCORE("double_score_power_up.png"),
		EAT_OBSTACLE("eat_obstacle_power_up.png"),
		EXTRA_LIFE("extra_life_power_up.jpg"),
		MIRRORED_MOVEMENT("reflect_power_up.png"),
		RANDOM_POWER_UP("random_power_up.png"),
		RANDOM_TELEPORT("random_teleport_power_up.png"),
		SCORE_UP("score_up_power_up.png"),
		SHIELD("shield_power_up.png"),
		SPEED_DOWN("speed_down_power_up.png"),
		SPEED_UP("speed_up_power_up.png");
		
		
		private String path; 
		
		private PowerUpType(final String path) {
			this.path = path;
		}
		
		public String getPath() {
			return this.path;
		}
}
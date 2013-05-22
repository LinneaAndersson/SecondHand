package com.secondhand.model.entity;


public final class GameSettings {
	private static GameSettings instance = new GameSettings();
	
	public boolean isMirroredMovement;
	public boolean hasEnemies;
	
	private GameSettings() { }
	

	public static GameSettings getInstance() {
		return instance;
	}	
}
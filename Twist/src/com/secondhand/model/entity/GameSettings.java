package com.secondhand.model.entity;


public final class GameSettings {
	private static GameSettings instance = new GameSettings();
	
	public boolean isMirroredMovement;
	public boolean hasEnemies;
	public boolean hasMusic;
	
	private GameSettings() { }
	

	public static GameSettings getInstance() {
		return instance;
	}	
}
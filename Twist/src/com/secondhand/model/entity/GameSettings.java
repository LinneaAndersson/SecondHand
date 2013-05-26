package com.secondhand.model.entity;

public final class GameSettings {
	private static GameSettings instance = new GameSettings();

	private boolean isMirroredMovement;
	private boolean hasEnemies;
	private boolean hasMusic;

	private GameSettings() {
	}

	public static GameSettings getInstance() {
		return instance;
	}

	public boolean isMirroredMovement() {
		return isMirroredMovement;
	}

	public boolean hasEnemies() {
		return hasEnemies;
	}

	public boolean hasMusic() {
		return hasMusic;
	}

	public void setMirroredMovement(final boolean isMirroredMovement) {
		this.isMirroredMovement = isMirroredMovement;
	}

	public void setHasEnemies(final boolean hasEnemies) {
		this.hasEnemies = hasEnemies;
	}

	public void setHasMusic(final boolean hasMusic) {
		this.hasMusic = hasMusic;
	}
}
package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Singelton class for describing the universe.
 */
public class Universe {
	private static Level currentLevel;

	// what level the player is on
	private static int levelNumber = 1;

	private static Universe instance;

	// perhaps create a tutorialLevel?
	private Universe() {
		currentLevel = new Level();
	}

	public static Universe getInstance() {
		if (instance == null) {
			instance = new Universe();
		}
		return instance;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public Level getLevel() {
		return currentLevel;
	}

	// TODO how to decide what to have on each successive level?
	public void nextLevel() {
		currentLevel = new Level();
	}

	public void update(Vector2 v) {

		if (currentLevel.checkPlayerBigEnough()) {

			nextLevel();

		} else {
			currentLevel.moveEntities(v);
		}
	}
}

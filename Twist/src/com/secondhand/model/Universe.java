package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Class for describing the universe.
 * 
 * 
 * I you make this class a singelton again, I will personally hunt you down and kill you - Eric
 
 */
public final class Universe {
	
	private Level currentLevel;

	//private static Universe instance;

	public Universe() {
		nextLevel();
	}
	
	public boolean isGameOver() {
		return this.currentLevel.isGameOver();
	}

	public Level getLevel() {
		return currentLevel;
	}

	public void nextLevel() {
		if (currentLevel == null) {
			// starting level
			currentLevel = new Level(2);
		} else {
			currentLevel = new Level(currentLevel.getLevelNumber()+1);
			// and also register all new entities in the controller. 
		}
	}

	public void onManagedUpdate(final float pSecondsElapsed) {
		if (currentLevel.checkPlayerBigEnough()) {
			nextLevel();
		} else {
			currentLevel.onManagedUpdate(pSecondsElapsed);
		}
	}

	public void update(final Vector2 v) {
		if (currentLevel.checkPlayerBigEnough()) {
			nextLevel();
		} else {
			currentLevel.sendTouchInput(v);
		}
	}
}

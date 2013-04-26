package com.secondhand.model;

import org.anddev.andengine.engine.Engine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.physics.PhysicsDestroyer;
import com.secondhand.resource.Sounds;

/**
 * Singelton class for describing the universe.
 */
public final class Universe {
	private Level currentLevel;


	// flag for when the game is over
	// check by update by gamePlayScene
	private boolean gameOver;

	private static Universe instance;

	private Universe() {
	}

	public void initialize(final Engine engine) {
		nextLevel();
		PhysicsDestroyer.getInstance().initialize(engine, currentLevel.getPhysicsWorld());
		gameOver = false;
	}

	public static Universe getInstance() {
		if (instance == null) {
			instance = new Universe();
		}
		return instance;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	// perhaps not needed if we only set gameover
	private void gameOver() {
		gameOver = true;
		// gameOver flag that gameplayScene checks each update
		// other gameOver stuff in this method

	}

	public Level getLevel() {
		return currentLevel;
	}

	public void nextLevel() {
		if (currentLevel == null) {
			currentLevel = new Level(2);
		} else if (!gameOver) {
			currentLevel = new Level(currentLevel.getLevelNumber());
		} else {
			gameOver = false;
			currentLevel = new Level();
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

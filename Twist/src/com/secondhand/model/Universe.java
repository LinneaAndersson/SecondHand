package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.scene.IGamePlaySceneView;

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
			
			MyDebug.d("advancing to next level");
			currentLevel.clearLevel();
			/*
			final IGamePlaySceneView view = currentLevel.getView();
			
			// clear physics world expect for player.
			
			
			currentLevel = new Level(currentLevel.getLevelNumber()+1);
			MyDebug.d("now we tell the view to create the level");
			view.newLevelStarted();
			// and also register all new entities in the controller. */
		}
	}

	
	
	// for debugging
	private boolean nextLevelAdvanced = false;
	
	public void onManagedUpdate(final float pSecondsElapsed) {
		if (currentLevel.checkPlayerBigEnough() && !nextLevelAdvanced) {
			nextLevelAdvanced = true;
			nextLevel();
		} else {
			currentLevel.onManagedUpdate(pSecondsElapsed);
		}
	}
	

	public void updateWithTouchInput(final Vector2 v) {
		currentLevel.sendTouchInput(v);
	}
}

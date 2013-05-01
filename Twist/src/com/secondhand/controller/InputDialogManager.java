package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

import com.secondhand.debug.MyDebug;
import com.secondhand.resource.HighScoreList;
import com.secondhand.resource.HighScoreList.Entry;
import com.secondhand.scene.GamePlayScene;
import com.secondhand.scene.IGameScene.AllScenes;

// manages the input dialog used when entering the player name. 
public final class InputDialogManager {
	private static InputDialogManager instance;

	private Engine engine;
	private MainActivity activity;

	public static String input;
	public static boolean showing = false;

	public static InputDialogManager getInstance() {
		if (instance == null) {
			instance = new InputDialogManager();
		}
		return instance;
	}

	private InputDialogManager() {
	}

	/**
	 * Setup this singelton class for usage.
	 * */
	public void initialize(final Engine engine, final MainActivity activity) {
		this.engine = engine;
		this.activity = activity;
	}

	public String getInput() {
		return input;
	}

	public void showDialog() {

		input = null;
		activity.runOnUiThread(new Runnable() {
			public void run() {
				// so it's deprecated, huh? Well fuck you android!
				activity.showDialog(MainActivity.TEXT_INPUT_DIALOG);
			}
		});
	}

	// TODO: holy fuck this code is ugly. Clean up.
	// get rid of all the static fields, for one.

	// we are doing it this way because it takes a whole freaking second
	// until
	// the operating system shows the damn input dialog.
	public void doStuff(GamePlayScene scene, float pSecondsElapsed) {
		if (input != null) {

			MyDebug.d("input string: " + input);
			HighScoreList.Entry newEntry = new HighScoreList.Entry(input, scene.getGameWorld().getPlayer().getScore());
			HighScoreList.getInstance().insertInHighScoreList(newEntry);
			showing = false;

			input = null;

			scene.switchScene(AllScenes.HIGH_SCORE_SCENE);

		} else if (showing) {
			scene.getGameWorld().onManagedUpdate(pSecondsElapsed);
		} else if (HighScoreList.getInstance().madeItToHighScoreList(
				scene.getGameWorld().getPlayer().getScore())) {

			showing = true;
			showDialog();

		} else {
			scene.switchScene(AllScenes.HIGH_SCORE_SCENE);
		}
	}
}

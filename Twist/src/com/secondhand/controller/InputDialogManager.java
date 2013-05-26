package com.secondhand.controller;

// manages the input dialog used when entering the player name. 
public final class InputDialogManager {
	private static InputDialogManager instance;

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

	/*
	 * Setup this singelton class for usage.
	 */
	public void initialize(final MainActivity activity) {
		this.activity = activity;
	}

	public String getInput() {
		return input;
	}

	@SuppressWarnings("deprecation")
	public void showDialog() {

		input = null;
		activity.runOnUiThread(new Runnable() {
			public void run() {
				activity.showDialog(MainActivity.TEXT_INPUT_DIALOG);
			}
		});
	}
}

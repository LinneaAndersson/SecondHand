package com.secondhand.controller;

import org.anddev.andengine.engine.Engine;

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

	private InputDialogManager() { }

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
			}});
	}
}

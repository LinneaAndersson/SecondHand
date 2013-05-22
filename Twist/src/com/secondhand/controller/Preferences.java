package com.secondhand.controller;

import android.app.Activity;
import android.content.SharedPreferences;

public final class Preferences {
	private static Preferences instance = new Preferences();
	
    public static final String PREFS_NAME = "app_settings.dat";
	
	private Preferences() { }
	
	private SharedPreferences.Editor editor;
	 
	public static Preferences getInstance() {
		return instance;
	}
	
	public void initialize(final Activity activity) {
		editor = activity.getSharedPreferences(PREFS_NAME	, 0).edit();
	}
	
	public SharedPreferences.Editor getEditor() {
		return this.editor;
	}
}

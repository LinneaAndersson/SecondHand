package com.secondhand.controller;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.entity.GameSettings;

import android.app.Activity;
import android.content.SharedPreferences;

public final class Preferences {
	private static Preferences instance = new Preferences();
	
    public static final String PREFS_NAME = "app_settings.dat";
    
	public static final String MIRRORED_MOVEMENT = "mirroredMovement";
	public static final String ENEMIES = "enemies";
	
	private Preferences() { }
	
	private SharedPreferences.Editor editor;
	private SharedPreferences preferences;
	 
	public static Preferences getInstance() {
		return instance;
	}
	
	public void initialize(final Activity activity) {
		preferences = activity.getSharedPreferences(PREFS_NAME	, 0);
		editor = preferences.edit();
		update();
	}
	
	private void update() {
		editor.commit();
		
		GameSettings.getInstance().hasEnemies = this.hasEnemies();
		GameSettings.getInstance().isMirroredMovement = this.isMirroredMovement();
		// update model here. 
	}
	
	public void setIsMirroredMovement(final boolean mirroredMovement) {
		this.editor.putBoolean(MIRRORED_MOVEMENT, mirroredMovement);
		update();
	}
	
	public void setHasEnemies(final boolean hasEnemies) {
		this.editor.putBoolean(ENEMIES, hasEnemies);
		update();
	}
	
	public boolean isMirroredMovement() {
		return preferences.getBoolean(MIRRORED_MOVEMENT, false);
	}

	public boolean hasEnemies() {
		return preferences.getBoolean(ENEMIES, true);
	}
}

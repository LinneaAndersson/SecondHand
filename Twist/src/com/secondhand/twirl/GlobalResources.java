package com.secondhand.twirl;

import org.anddev.andengine.opengl.font.Font;

import com.secondhand.loader.FontLoader;

import android.graphics.Color;
import android.graphics.Typeface;

/**
 * Singelton class for managing the global resources of the app.
 * These are resources that are used in several scenes, ie, the menu font. 
 */
public class GlobalResources {
	
	public Font menuFont;
	
	private static GlobalResources instance = null;
	
	public static GlobalResources getInstance() {
		if(instance == null) {
			instance = new GlobalResources();
		}
		return instance;
	}

	public void load() {	
		this.menuFont = FontLoader.getInstance().loadFont(
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, Color.WHITE);
	}
}

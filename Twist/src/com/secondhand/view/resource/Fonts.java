package com.secondhand.view.resource;

import org.anddev.andengine.opengl.font.Font;

import android.graphics.Color;
import android.graphics.Typeface;

import com.secondhand.view.resource.loader.FontLoader;

public final class Fonts {
	

	public Font menuItemFont;
	public Font menuHeadlineFont;
	
	private static Fonts instance;
	
	public static Fonts getInstance() {
		if(instance == null) {
			instance = new Fonts();
		}
		return instance;
	}
	
	public void load() {
		this.menuItemFont = FontLoader.getInstance().loadFont(
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, Color.WHITE);
		this.menuHeadlineFont = FontLoader.getInstance().loadFont(
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 50, Color.WHITE);
	
	}
	
}

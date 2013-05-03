package com.secondhand.view.scene;

import org.anddev.andengine.engine.Engine;

import android.content.Context;

import com.secondhand.model.resource.LocalizationStrings;


public class SettingsMenuScene extends GameMenuScene{
	

	public SettingsMenuScene(final Engine engine, final Context context) {
		super(engine, context);
	}
	
	@Override
	public void loadScene() {
	
		// layout headline
		layoutHeadline(LocalizationStrings.getInstance()
				.getLocalizedString("menu_settings"));
	}

	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}

}

package com.secondhand.view.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import com.secondhand.view.resource.LocalizationStrings;
import com.secondhand.view.scene.GameMenuScene.MenuItem;

import android.content.Context;

public class OptionsScene extends GameMenuScene {

	public static final int MIRRORED_MOVEMENT_FALSE = 0;
	public static final int MIRRORED_MOVEMENT_TRUE = 1;
	public OptionsScene(Engine engine, Context context) {
		super(engine, context);

	}
	
	@Override
	public void loadScene() {
		super.loadScene();
		
		final int menuStartX = layoutHeadline("Options");
	
	new MenuItem(MIRRORED_MOVEMENT_FALSE, LocalizationStrings.getInstance().getLocalizedString("menu_new_game"));
	new MenuItem(MIRRORED_MOVEMENT_TRUE, LocalizationStrings.getInstance().getLocalizedString("menu_new_game"));
	}

	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}

}

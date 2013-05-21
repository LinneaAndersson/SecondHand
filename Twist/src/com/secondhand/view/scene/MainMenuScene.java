package com.secondhand.view.scene;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.engine.Engine;

import android.content.Context;

import com.secondhand.view.resource.LocalizationStrings;

public class MainMenuScene extends GameMenuScene  {

	public static final int MENU_NEW_GAME = 0;
	public static final int MENU_HIGH_SCORE = 2;
	public static final int MENU_OPTION = 3;
	public static final int MENU_INSTRUCTION = 4;
	
	public MainMenuScene(final Engine engine, final Context context) {
		super(engine, context);
	}

	@Override
	public void loadScene() {
		super.loadScene();
		
		final int menuStartX = layoutHeadline("Twirl");
		
		
		// make a centered menu.
		final List<GameMenuScene.MenuItem> menuItems = new ArrayList<GameMenuScene.MenuItem>();
		
		menuItems.add(new MenuItem(MENU_NEW_GAME, LocalizationStrings.getInstance().getLocalizedString("menu_new_game")));
		menuItems.add(new MenuItem(MENU_HIGH_SCORE, LocalizationStrings.getInstance().getLocalizedString("menu_high_score")));
		menuItems.add(new MenuItem(MENU_OPTION, LocalizationStrings.getInstance().getLocalizedString("menu_options")));
		menuItems.add(new MenuItem(MENU_INSTRUCTION, LocalizationStrings.getInstance().getLocalizedString("menu_instructions")));
		
		layoutCenteredMenu(menuStartX, menuItems);
	}


	
	@Override
	public AllScenes getParentScene() {
		// see the getParentScene method of LoadingScene for a motivation of why null
		// should be returned here.
		return null;
	}
	
	public void onSwitchScene() {
	}
	


}

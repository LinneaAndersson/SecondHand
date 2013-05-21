package com.secondhand.view.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;

import com.secondhand.view.resource.Fonts;
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
		
		final Font mFont = Fonts.getInstance().menuItemFont;
		
		final Text options = new Text(100, 60, mFont, LocalizationStrings
				.getInstance().getLocalizedString("menu_options"),
				HorizontalAlign.CENTER);
		options.setScale(1.5f);

		this.attachChild(options);
	
	new MenuItem(MIRRORED_MOVEMENT_FALSE, LocalizationStrings.getInstance().getLocalizedString("menu_new_game"));
	new MenuItem(MIRRORED_MOVEMENT_TRUE, LocalizationStrings.getInstance().getLocalizedString("menu_new_game"));
	}

	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}

}

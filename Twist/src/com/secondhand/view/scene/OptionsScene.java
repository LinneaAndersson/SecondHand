package com.secondhand.view.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;

import android.content.Context;

import com.secondhand.view.resource.Fonts;
import com.secondhand.view.resource.LocalizationStrings;

public class OptionsScene extends GameMenuScene {
	public static final int MIRRORED_MOVEMENT_FALSE = 0;
	public static final int MIRRORED_MOVEMENT_TRUE = 1;
	public static final int ENEMIES_TRUE = 2;
	public static final int ENEMIES_FALSE = 3;

	private IMenuItem enemiesOn;
	private IMenuItem enemiesOff;

	private IMenuItem mirroredMovementOn;
	private IMenuItem mirroredMovementOff;

	public OptionsScene(final Engine engine, final Context context) {
		super(engine, context);

	}

	@Override
	public void loadScene() {
		super.loadScene();

		final Text options;
		final Text mirroredMovement;
		final Text enemy;

		final Font mFont = Fonts.getInstance().menuItemFont;

		options = new Text(100, 60, mFont, LocalizationStrings.getInstance()
				.getLocalizedString("menu_options"), HorizontalAlign.CENTER);
		options.setScale(1.5f);

		mirroredMovement = new Text(100, 150, mFont, LocalizationStrings
				.getInstance().getLocalizedString("mirrored_movement"),
				HorizontalAlign.CENTER);

		enemy = new Text(100, 300, mFont, LocalizationStrings.getInstance()
				.getLocalizedString("enemy"), HorizontalAlign.CENTER);

		this.attachChild(enemy);
		this.attachChild(options);
		this.attachChild(mirroredMovement);

		layoutButtons();
	}

	private void layoutButtons() {

		// enemies button
		final Font menuItemFont = Fonts.getInstance().menuItemFont;

		enemiesOn = new TextMenuItem(ENEMIES_TRUE, menuItemFont,
				LocalizationStrings.getInstance().getLocalizedString("on"));
		enemiesOff = new TextMenuItem(ENEMIES_FALSE, menuItemFont,
				LocalizationStrings.getInstance().getLocalizedString("off"));

		enemiesOn.setPosition(100, 350);
		addMenuItem(enemiesOn);

		enemiesOff.setPosition(200, 350);
		addMenuItem(enemiesOff);

		mirroredMovementOn = new TextMenuItem(MIRRORED_MOVEMENT_TRUE,
				menuItemFont, LocalizationStrings.getInstance()
						.getLocalizedString("on"));
		mirroredMovementOff = new TextMenuItem(MIRRORED_MOVEMENT_FALSE,
				menuItemFont, LocalizationStrings.getInstance()
						.getLocalizedString("off"));

		// look better if both on and of buttons have the same alignment
		mirroredMovementOn.setPosition(100, 200);
		addMenuItem(mirroredMovementOn);

		mirroredMovementOff.setPosition(200, 200);
		addMenuItem(mirroredMovementOff);
	}

	public void setHasEnemies(final boolean hasEnemies) {
		if (hasEnemies) {
			enemiesOff.setColor(0.5f, 0.5f, 0.5f);
			enemiesOn.setColor(1f, 1f, 1f);
		} else {
			enemiesOn.setColor(0.5f, 0.5f, 0.5f);
			enemiesOff.setColor(1f, 1f, 1f);
		}
	}

	public void setMirroredMovement(final boolean isMirroredMovement) {
		if (isMirroredMovement) {
			mirroredMovementOff.setColor(0.5f, 0.5f, 0.5f);
			mirroredMovementOn.setColor(1f, 1f, 1f);
		} else {
			mirroredMovementOn.setColor(0.5f, 0.5f, 0.5f);
			mirroredMovementOff.setColor(1f, 1f, 1f);
		}
	}

	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}

}

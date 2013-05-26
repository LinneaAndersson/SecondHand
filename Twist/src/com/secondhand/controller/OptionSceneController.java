package com.secondhand.controller;

import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import com.secondhand.view.scene.OptionsScene;

public class OptionSceneController implements IOnMenuItemClickListener {
	private final OptionsScene view;

	public OptionSceneController(final OptionsScene optionsScene) {
		super();
		this.view = optionsScene;
		view.setOnMenuItemClickListener(this);

		optionsScene.setHasEnemies(Preferences.getInstance().hasEnemies());
		optionsScene.setMirroredMovement(Preferences.getInstance()
				.isMirroredMovement());
		optionsScene.setMusic(Preferences.getInstance().hasMusic());
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene,
			final IMenuItem pMenuItem, final float pMenuItemLocalX,
			final float pMenuItemLocalY) {
		if (pMenuItem.getID() == OptionsScene.MIRRORED_MOVEMENT_TRUE) {
			// now set in the model
			setIsMirroredMovement(true);
			return true;
		} else if (pMenuItem.getID() == OptionsScene.MIRRORED_MOVEMENT_FALSE) {
			// now set it in the model
			setIsMirroredMovement(false);
			return true;
		} else if (pMenuItem.getID() == OptionsScene.ENEMIES_TRUE) {
			// now in the model
			setHasEnemies(true);
		} else if (pMenuItem.getID() == OptionsScene.ENEMIES_FALSE) {
			setHasEnemies(false);
		} else if (pMenuItem.getID() == OptionsScene.MUSIC_ON) {
			setHasMusic(true);
		} else if (pMenuItem.getID() == OptionsScene.MUSIC_OFF) {
			setHasMusic(false);
		}
		return false;
	}

	private void setIsMirroredMovement(final boolean mirroredMovement) {
		// set prefences.
		view.setMirroredMovement(mirroredMovement);
		Preferences.getInstance().setIsMirroredMovement(mirroredMovement);
	}

	private void setHasEnemies(final boolean enemies) {
		view.setHasEnemies(enemies);
		Preferences.getInstance().setHasEnemies(enemies);

	}

	private void setHasMusic(final boolean music) {
		view.setMusic(music);
		Preferences.getInstance().setHasMusic(music);
	}

}

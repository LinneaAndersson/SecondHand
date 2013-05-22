package com.secondhand.controller;

import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import com.secondhand.view.scene.OptionsScene;

public class OptionSceneController implements IOnMenuItemClickListener{
	private final OptionsScene view;
	

	public OptionSceneController(final OptionsScene optionsScene) {
		super();
		this.view = optionsScene;
		view.setOnMenuItemClickListener(this);
		setIsMirroredMovement(false);
	
		optionsScene.setHasEnemies(Preferences.getInstance().hasEnemies());

		optionsScene.setMirroredMovement(Preferences.getInstance().isMirroredMovement());
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem,
			final float pMenuItemLocalX, final float pMenuItemLocalY) {
		if (pMenuItem.getID() == OptionsScene.MIRRORED_MOVEMENT_TRUE){
			setIsMirroredMovement(true);
			return true;
		} else if (pMenuItem.getID() == OptionsScene.MIRRORED_MOVEMENT_FALSE){
			setIsMirroredMovement(false);
			return true;
		} else if(pMenuItem.getID() == OptionsScene.ENEMIES_TRUE){
			setHasEnemies(true);
		} else if(pMenuItem.getID() == OptionsScene.ENEMIES_FALSE){
			setHasEnemies(false);
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

}

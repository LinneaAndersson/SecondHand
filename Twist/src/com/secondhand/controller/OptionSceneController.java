package com.secondhand.controller;

import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import com.secondhand.view.scene.OptionsScene;

public class OptionSceneController implements IOnMenuItemClickListener{
	private final OptionsScene view;
	public static boolean isMirroredMovement;
	public static boolean hasEnemies;

	public OptionSceneController(final OptionsScene optionsScene) {
		super();
		this.view = optionsScene;
		view.setOnMenuItemClickListener(this);
		setIsMirroredMovement(false);
	
		optionsScene.setHasEnemies(true);

		optionsScene.setMirroredMovement(true);
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem,
			final float pMenuItemLocalX, final float pMenuItemLocalY) {
		if (pMenuItem.getID() == OptionsScene.MIRRORED_MOVEMENT_TRUE){
			// now set in the model 
			setIsMirroredMovement(true);
			return true;
		} else if (pMenuItem.getID() == OptionsScene.MIRRORED_MOVEMENT_FALSE){
			// now set it in the model
			setIsMirroredMovement(false);
			return true;
		} else if(pMenuItem.getID() == OptionsScene.ENEMIES_TRUE){
			// now in the model
			setHasEnemies(true);
		} else if(pMenuItem.getID() == OptionsScene.ENEMIES_FALSE){
			// now set in the model
			setHasEnemies(false);
		}
		return false;
	}

	private void setIsMirroredMovement(final boolean mirroredMovement) {
		isMirroredMovement = mirroredMovement;
		view.setMirroredMovement(isMirroredMovement);
	}
	
	private void setHasEnemies(final boolean enemies) {
		hasEnemies = enemies;
		view.setHasEnemies(enemies);
		
	}

}

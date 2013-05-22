package com.secondhand.controller;

import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import com.secondhand.view.scene.OptionsScene;

public class OptionSceneController implements IOnMenuItemClickListener{
	private final OptionsScene view;
	public static boolean isMirroredMovement;
	public static boolean hasEnemies;

	public OptionSceneController(final OptionsScene optionsScene,
			final SceneController sceneController) {
		super();
		this.view = optionsScene;
		view.setOnMenuItemClickListener(this);
		setIsMirroredMovement(false);
		setHasEnemies(true);
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

	private void setIsMirroredMovement(boolean mirroredMovement) {
		isMirroredMovement = mirroredMovement;
		view.setMirroredMovementColor(isMirroredMovement);
	}
	
	private void setHasEnemies(boolean enemies) {
		hasEnemies = enemies;
		view.setHasEnemiesColor(enemies);
		
	}

}

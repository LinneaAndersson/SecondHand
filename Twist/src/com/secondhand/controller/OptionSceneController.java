package com.secondhand.controller;

import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import com.secondhand.debug.MyDebug;
import com.secondhand.view.scene.OptionsScene;

public class OptionSceneController implements IOnMenuItemClickListener{
	private final OptionsScene view;
	public static boolean isMirroredMovement;
	public static boolean hasEnemies;

	public OptionSceneController(OptionsScene optionsScene,
			SceneController sceneController) {
		super();
		this.view = optionsScene;
		view.setOnMenuItemClickListener(this);
		setIsMirroredMovement(false);
		setHasEnemies(true);
	}


	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
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

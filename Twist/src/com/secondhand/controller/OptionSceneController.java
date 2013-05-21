package com.secondhand.controller;

import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import com.secondhand.view.scene.OptionsScene;

public class OptionSceneController implements IOnMenuItemClickListener{
	private final OptionsScene view;
	public static boolean isMirroredMovement;

	public OptionSceneController(OptionsScene optionsScene,
			SceneController sceneController) {
		super();
		this.view = optionsScene;
		isMirroredMovement= false;
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		if (pMenuItem.getID() == OptionsScene.MIRRORED_MOVEMENT_TRUE){
			isMirroredMovement = true;
			return true;
		} else if (pMenuItem.getID() == OptionsScene.MIRRORED_MOVEMENT_FALSE){
			isMirroredMovement = false;
			return true;
		}
		return false;
	}

}

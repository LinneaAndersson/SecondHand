package com.secondhand.controller;

import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import com.secondhand.debug.MyDebug;
import com.secondhand.view.scene.AllScenes;
import com.secondhand.view.scene.MainMenuScene;

public class MainMenuSceneController implements IOnMenuItemClickListener {

	private final SceneController sceneController;
	
	public MainMenuSceneController(final MainMenuScene mainMenuScene, final SceneController sceneController) {
		mainMenuScene.setOnMenuItemClickListener(this);
		this.sceneController = sceneController;
	}
	
	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem,
			
			final float pMenuItemLocalX, final float pMenuItemLocalY) {
		
		switch(pMenuItem.getID()) {
		case MainMenuScene.MENU_NEW_GAME:
			MyDebug.i("now the game should start");
			
			sceneController.switchScene(AllScenes.LEVEL_LOADING_SCENE);
			
			// start new game
			/*LoadingScene loadingScene = (LoadingScene) SceneManager.getInstance().getScene(AllScenes.LOADING_SCENE);
			
			loadingScene.setText("loading_level");
			loadingScene.setSceneEnum(AllScenes.GAME_PLAY_SCENE);*/
			// TODO: implement the scene switches
			//setScene(AllScenes.LOADING_SCENE);
			return true;
		case MainMenuScene.MENU_HIGH_SCORE:
			sceneController.switchScene(AllScenes.HIGH_SCORE_SCENE);
			return true;
		default:
			return false;
		}
	}
}

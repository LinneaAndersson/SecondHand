package com.secondhand.scene;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import android.view.KeyEvent;

import com.secondhand.controller.SceneManager;
import com.secondhand.controller.SceneManager.AllScenes;
import com.secondhand.debug.MyDebug;

public class SettingsMenuScene extends GameMenuScene implements IOnMenuItemClickListener {

	private static final int MENU_VOLUME = 0;
	
	public SettingsMenuScene(Camera camera) {
		super(camera);
	}

	@Override
	public void loadResources() {
	}

	@Override
	public void loadScene() {
		
		this.setBackground(new ColorBackground(0, 0, 0));
		
		// make a centered menu.
		List<GameMenuScene.MenuItem> menuItems = new ArrayList<GameMenuScene.MenuItem>();
		menuItems.add(new MenuItem(MENU_VOLUME, "volume"));
		
		layoutCenteredMenu(menuItems);
		this.setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		switch(pMenuItem.getID()) {
		case MENU_VOLUME:
			MyDebug.i("volume!");
			return true;
		default:
			return false;
		}

	}
	
	/*
	 * Fixed so that the SettingsMenuScene goes back to the MainMenuScene when
	 * the user presses "back" on the phone.
	 * @see com.secondhand.scene.GameMenuScene#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		if (pKeyCode == KeyEvent.KEYCODE_BACK && pEvent.getAction() == KeyEvent.ACTION_DOWN) { // User has pressed Down the Back key
			SceneManager.getInstance().setCurrentSceneEnum(AllScenes.MAIN_MENU_SCENE); // Set Scene = MainMenuScene
			return true;
		} else {
			return false; // Else: let super deal with it
		}
	}
}

package com.secondhand.scene;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;

import com.secondhand.debug.MyDebug;
import com.secondhand.twirl.GlobalResources;

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

}

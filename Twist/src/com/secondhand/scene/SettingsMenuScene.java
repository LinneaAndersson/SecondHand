package com.secondhand.scene;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;

import android.view.KeyEvent;

import com.secondhand.controller.SceneManager;
import com.secondhand.controller.SceneManager.AllScenes;
import com.secondhand.debug.MyDebug;
import com.secondhand.twirl.GlobalResources;

public class SettingsMenuScene extends GameMenuScene implements IOnMenuItemClickListener {

	private static final int MENU_HIGHER = 0;
	private static final int MENU_LOWER = 1;
	
	public SettingsMenuScene(Camera camera) {
		super(camera);
	}

	@Override
	public void loadResources() {
	}

	@Override
	public void loadScene() {
		
		// layout headline
		int menuStartY = layoutHeadline("Settings");	
		
		
		// constants
		
		// the x-position of the settings items. 
		final int x = 100;
		// ie, the spacing between volume and the actual volume control.
		final int subheadlineInbetweenSpacing = 5;
		
		final Font font = GlobalResources.getInstance().menuItemFont;
		final float fontHeight = new Text(0, 0, font, "lorem ipsum").getHeight();
		
		// now layout the menu items:
		int y = menuStartY;
		
		// volume subheading
		Text volumeText = new Text(x,y, font, "volume");
		this.attachChild(volumeText);
		y += fontHeight + subheadlineInbetweenSpacing;
		
		// the actual volume control:
		// TODO: make it look prettier.
		
		Text volumeAmountText = new Text(x,y, font, "100%");
		this.attachChild(volumeAmountText);
		y += fontHeight + subheadlineInbetweenSpacing;
		
		IMenuItem higherItem = new TextMenuItem(MENU_HIGHER, font, "Higher");
		higherItem.setPosition(x, y);
		addMenuItem(higherItem);
		y += fontHeight + subheadlineInbetweenSpacing;
		
		IMenuItem lowerItem = new TextMenuItem(MENU_LOWER, font, "Lower");
		lowerItem.setPosition(x, y);
		addMenuItem(lowerItem);
		
		this.setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		switch(pMenuItem.getID()) {
		case MENU_HIGHER:
			MyDebug.i("higher volume")
			raiseVolume();
			return true;
		case MENU_LOWER:
			lowerVolume();
			MyDebug.i("Lower volume");
			return true;
		default:
			return false;
		}

	}

	@Override
	public AllScenes get_parent() {
		return AllScenes.MAIN_MENU_SCENE;
	}
	
}

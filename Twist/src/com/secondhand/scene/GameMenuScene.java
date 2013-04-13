package com.secondhand.scene;

import java.util.List;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;

import android.view.KeyEvent;

import com.secondhand.controller.IGameScene;
import com.secondhand.debug.MyDebug;
import com.secondhand.twirl.GlobalResources;

/**
 * Base class for all menu scenes.
 */
public abstract class GameMenuScene extends MenuScene implements IGameScene{

	public GameMenuScene(Camera camera) { 
		super(camera);
	}
	
	
	
	@Override
	public Scene getScene() {
		return this;
	}
		
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		return false;
	}
	
	/**
	 * The separate menu items are laid out centered horizontally. All these items are stacked up on each
	 * other and this stack is centered vertically. 
	 */
	protected void layoutCenteredMenu(final List<GameMenuScene.MenuItem> menuItems) {
	
		final Font menuItemFont = GlobalResources.getInstance().menuItemFont;
		final Font menuHeadlineFont = GlobalResources.getInstance().menuItemFont;
		
		// vertical spacing between separate menu items
		final float spacingBetweenItems = 20;
		
		// the vertical spacing around the headline.
		final float headlineSpacing = 40;
		
		final float fontHeight = new Text(0, 0, menuItemFont, "lorem ipsum").getHeight();
		final float menuHeight = menuItems.size() * fontHeight + (menuItems.size() - 1)  * spacingBetweenItems;
		
		// y-coordinate of the menu item.
		float y = this.mCamera.getHeight() / 2.0f - menuHeight / 2.0f;
				
		for(GameMenuScene.MenuItem menuItem : menuItems) {
			
			MyDebug.d("laying single menu item");
			
			IMenuItem item = new TextMenuItem(menuItem.id, menuItemFont, menuItem.text);
			
			final float x = this.mCamera.getWidth() / 2.0f - item.getWidth() / 2.0f;
			
			item.setPosition(x, y);
			addMenuItem(item);
			
			// to the next row.
			y += item.getHeight() + spacingBetweenItems;
			
		}
	}
	
	public static class MenuItem {
		public int id;
		public String text;
		
		public MenuItem(final int id, final String text) {
			this.id = id;
			this.text = text;
		}
	}
}

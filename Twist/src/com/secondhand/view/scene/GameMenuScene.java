package com.secondhand.view.scene;

import java.util.List;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;

import android.content.Context;

import com.secondhand.view.resource.Fonts;

/*
 * Base class for all menu scenes.
 */
public abstract class GameMenuScene extends MenuScene implements IGameScene {

	protected final SmoothCamera smoothCamera;
	protected final Engine engine;
	protected final Context context;
	private boolean isLoaded;

	public GameMenuScene(final Engine engine, final Context context) {
		super(engine.getCamera());

		// we do this to keep the API consistent
		this.smoothCamera = (SmoothCamera) this.mCamera;
		this.context = context;
		this.engine = engine;
	}

	protected int layoutHeadline(final String headline) {
		// the vertical spacing around the headline.
		final int headlineSpacing = 40;

		final Font menuHeadlineFont = Fonts.getInstance().menuHeadlineFont;

		final float fontHeight = new Text(0, 0, menuHeadlineFont, "lorem ipsum")
				.getHeight();

		// y-coordinate of the menu item.
		final float y = headlineSpacing;

		final Text headlineText = new Text(0, y, menuHeadlineFont, headline);

		final float x = this.smoothCamera.getWidth() / 2.0f
				- headlineText.getWidth() / 2.0f;
		headlineText.setPosition(x, y);

		this.attachChild(headlineText);

		return (int) fontHeight + headlineSpacing * 2 - 50;
	}

	/*
	 * The separate menu items are laid out centered horizontally. All these
	 * items are stacked up on each other and this stack is centered vertically.
	 */
	protected void layoutCenteredMenu(final int startY,
			final List<GameMenuScene.MenuItem> menuItems) {

		final Font menuItemFont = Fonts.getInstance().menuItemFont;

		// vertical spacing between separate menu items
		final float spacingBetweenItems = 20;

		final float fontHeight = new Text(0, 0, menuItemFont, "lorem ipsum")
				.getHeight();
		final float menuHeight = menuItems.size() * fontHeight
				+ (menuItems.size() - 1) * spacingBetweenItems;

		// y-coordinate of the menu item.
		float y = (this.smoothCamera.getHeight() - startY) / 2.0f - menuHeight
				/ 2.0f + startY;

		for (final GameMenuScene.MenuItem menuItem : menuItems) {
			final IMenuItem item = new TextMenuItem(menuItem.id, menuItemFont,
					menuItem.text);

			final float x = this.smoothCamera.getWidth() / 2.0f
					- item.getWidth() / 2.0f;

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

	@Override
	public void loadScene() {
		this.detachChildren();
		this.isLoaded = true;
	}

	@Override
	public boolean isLoaded() {
		return this.isLoaded;
	}

	@Override
	public Scene getScene() {
		return this;
	}

	@Override
	public void onSwitchScene() {
		this.isLoaded = false;
	}
}

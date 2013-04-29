package com.secondhand.scene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.ui.dialog.StringInputDialogBuilder;
import org.anddev.andengine.util.HorizontalAlign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.secondhand.controller.SceneManager;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.resource.Fonts;
import com.secondhand.resource.LocalizationStrings;

public class GameOverScene extends GameMenuScene implements
IOnMenuItemClickListener, TextWatcher {
	private Font mFont;
	private Player player;
	private Engine mEngine;
	private static final int NAME = 0;
	private static final int SKIP = 1;
	private static final int SAVE = 2;
	private BufferedReader reader;
	Text textGameOver = null;
	private Context mcontext;
	private StringInputDialogBuilder sib;
	private GameWorld gameWorld;

	public void setGameWorld(final GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public GameOverScene(final Engine engine, final Context context) {
		super(engine, context);
		mcontext=context;
		//EditText eT = 

		
	}

	@SuppressLint("NewApi")
	@Override
	public void loadScene() {
		setGameWorld(SceneManager.getInstance().getGamePlayScene().getGameWorld());
		String mLine = "";
		int antal = 0;
		player = gameWorld.getPlayer();

		
		this.mFont = Fonts.getInstance().menuItemFont;

		try {
			reader = new BufferedReader(new InputStreamReader(context
					.getAssets().open("highScore")));

			mLine = reader.readLine();
			// checks if the score of the player are the top 5 score, and if it
			// is it prints contgratulation. other way game over.
			while (true) {
				antal++;
				mLine = reader.readLine().trim();
				MyDebug.d(mLine);

				if (player.getScore() > Integer.parseInt(mLine)) {
					setHeadLine("congratulation!");
					setCenterText(150,"You are top 5!");
					break;
				}


				mLine = reader.readLine();

				if (mLine == null && antal < 5) {
					setHeadLine("congratulation");
					setCenterText(100,"You are top 5!");
					break;
				} else if (antal >= 5) {
					setHeadLine("menu_game_over");
					setCenterText(150,"Try  again");
					break;
				}
			}
		} catch (IOException e) {
			Log.e("GameOverScene", "laodScene");
		}
		final List<MenuItem> menuList = new ArrayList();
		menuList.add(new MenuItem(NAME, LocalizationStrings.getInstance()
				.getLocalizedString("game_over_name")));
		menuList.add(new MenuItem(SKIP, LocalizationStrings.getInstance()
				.getLocalizedString("game_over_skip")));
		menuList.add(new MenuItem(SAVE, LocalizationStrings.getInstance()
				.getLocalizedString("game_over_save")));

		layoutCenteredMenu(antal, menuList);
		this.setOnMenuItemClickListener(this);
	}

	public void setHeadLine(final String text) {
		textGameOver = new Text(100, 60, mFont, LocalizationStrings
				.getInstance().getLocalizedString(text), HorizontalAlign.CENTER);

		final float x = this.smoothCamera.getWidth() / 2.0f
				- textGameOver.getWidth() / 2.0f;
		final float y = this.smoothCamera.getHeight() / 2.0f
				- textGameOver.getHeight() / 2.0f;
		textGameOver.setPosition(x, (int) (0.2 * y));
		textGameOver.setScale(1.2f);

		this.attachChild(textGameOver);

	}

	public void setCenterText(int whereY, String textName){
		Text text = new Text(100,60,mFont,textName,HorizontalAlign.CENTER);
		final float x = this.smoothCamera.getWidth() / 2.0f
				- text.getWidth() / 2.0f;
		final float y = this.smoothCamera.getHeight() / 2.0f
				- text.getHeight() / 2.0f;
		text.setPosition(x, (int) whereY);
		
		this.attachChild(text);		
	}
	
	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene,
			final IMenuItem pMenuItem, final float pMenuItemLocalX,
			final float pMenuItemLocalY) {

		switch (pMenuItem.getID()) {
		case SKIP:
			setScene(getParentScene());
			break;
		case SAVE:
			setScene(AllScenes.HIGH_SCORE_SCENE);
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

}

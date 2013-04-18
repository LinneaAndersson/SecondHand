package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;

import android.content.Context;

import com.secondhand.model.Player;
import com.secondhand.twirl.GlobalResources;
import com.secondhand.twirl.LocalizationStrings;

public class GameOverScene extends GameMenuScene implements
		IOnMenuItemClickListener {
	Font mFont;
	private Player player;
	private static final int NAME = 0;
	private static final int SKIP = 1;
	private int[] highScore;
	private String[] highScoreName;

	public GameOverScene(Engine engine, Context context) {
		super(engine, context);
		//this.player = player;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadResources() {
		mFont = GlobalResources.getInstance().menuItemFont;
		highScore=LocalizationStrings
				.getInstance().getLocalizedIntArray("high_score");
		highScoreName=LocalizationStrings
				.getInstance().getLocalizedStringArray("high_score_name_list");
		
	}

	@Override
	public void loadScene() {
		int tmp = 0;
		Text textGameOver;

		// The title
		//just trying to get this working. later this will check if
		//you are top 5.(top 5 will be on the high-score-list).
		if(0 < highScore[1]){

				textGameOver = new Text(100, 60, mFont, LocalizationStrings
					.getInstance().getLocalizedString("menu_game_over"),
					HorizontalAlign.CENTER);
		} else {
				textGameOver = new Text(100, 60, mFont, LocalizationStrings
					.getInstance().getLocalizedString("congratulations"),
					HorizontalAlign.CENTER);
		}
		
		float x = this.smoothCamera.getWidth() / 2.0f - textGameOver.getWidth()
				/ 2.0f;
		float y = this.smoothCamera.getHeight() / 2.0f - textGameOver.getHeight()
				/ 2.0f;
		textGameOver.setPosition(x, (int) (0.2 * y));

		this.attachChild(textGameOver);


	}

	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		// TODO Auto-generated method stub
		return false;
	}

}

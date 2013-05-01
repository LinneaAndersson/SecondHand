package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;

import android.content.Context;

import com.secondhand.debug.MyDebug;
import com.secondhand.resource.Fonts;
import com.secondhand.resource.HighScoreList;
import com.secondhand.resource.HighScoreList.Entry;
import com.secondhand.resource.LocalizationStrings;


public class HighScoreScene extends GameScene {

	// position of the high score table
	private float x;
	private float y;
	
	// index so far in the high score table.
	private int i = 0;

	private float timer;
	
	public static final float SECONDS_PER_HIGH_SCORE_ENTRY = 0.5f;
	
	public HighScoreScene(final Engine engine, final Context context) {
		super(engine, context);
	}

	@Override
	public void loadScene() {
		super.loadScene();
		
		final Font mFont = Fonts.getInstance().menuItemFont;
		// The title
		final Text highScore = new Text(100, 60, mFont, LocalizationStrings
				.getInstance().getLocalizedString("menu_high_score"),
				HorizontalAlign.CENTER);
		highScore.setScale(1.5f);

		this.attachChild(highScore);

		x = this.smoothCamera.getWidth() / 2.0f-120;
		y = this.smoothCamera.getHeight() / 2.0f-20;
		
		
		timer = 0;
		i = 0;
	}
	
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		
		timer += pSecondsElapsed;

		MyDebug.d("second: " + pSecondsElapsed);
		
		MyDebug.d("timer: " + timer);
		
		if(timer > SECONDS_PER_HIGH_SCORE_ENTRY) {
			
			timer = 0;
			
			if(i == HighScoreList.getInstance().getHighScoreList().size()) {
				this.isLoaded = false;
				super.setScene(AllScenes.MAIN_MENU_SCENE);
				return;
			}

			final Entry entry =  HighScoreList.getInstance().getHighScoreList().get(i);

			final Text playerScoreName = new Text(x,y,Fonts.getInstance().menuItemFont, (i+1) + ". " + entry.name);
			final Text playerScore = new Text(x, y,
					Fonts.getInstance().menuItemFont, entry.score + "");
			// increase the y-axis for every player. Max 5 players! 
			playerScoreName.setPosition(x, (int) (y * (0.35 + (i+1) * 0.3)));
			// x-constant because the name cannot be bigger than "220"(e.g x+220)
			playerScore.setPosition(x+220, (int) (y * (0.35 + (i+1) * 0.3)));

			this.attachChild(playerScore);
			this.attachChild(playerScoreName);

			// next entry in the next showing. 
			i++;
		}

	}

	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}

}

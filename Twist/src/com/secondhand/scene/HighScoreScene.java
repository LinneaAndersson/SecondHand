package com.secondhand.scene;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

	
		//We know which position the name will begin and where the score will end.
		final float x = this.smoothCamera.getWidth() / 2.0f-120;
		final 	float y = this.smoothCamera.getHeight() / 2.0f-20;

			for(int i = 0; i < HighScoreList.getInstance().getHighScoreList().size(); ++i) {
				
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
			}
			
	}

	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}

}

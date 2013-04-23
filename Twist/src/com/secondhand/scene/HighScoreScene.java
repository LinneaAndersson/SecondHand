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
import com.secondhand.resource.LocalizationStrings;


public class HighScoreScene extends GameScene {
	private Font mFont;

	public HighScoreScene(final Engine engine, final Context context) {
		super(engine, context);
	}

	@Override
	public void loadResources() {
		mFont = Fonts.getInstance().menuItemFont;
	}

	@Override
	public void loadScene() {

		int tmp = 0;

		// The title
		final Text highScore = new Text(100, 60, mFont, LocalizationStrings
				.getInstance().getLocalizedString("menu_high_score"),
				HorizontalAlign.CENTER);

		// The coordinates for the text to bee in the middle of the screen
		float x = this.smoothCamera.getWidth() / 2.0f - highScore.getWidth()
				/ 2.0f;
		float y = this.smoothCamera.getHeight() / 2.0f - highScore.getHeight()
				/ 2.0f;
		highScore.setPosition(x, (int) (0.2 * y));

		this.attachChild(highScore);

		// read from highScore-file in the asset-folder
		try {
			final BufferedReader reader = new BufferedReader(new InputStreamReader(
					context.getAssets().open("highScore")));
			String mLine = reader.readLine();

			// to get the coordinates for position.
			final Text highScoreText = new Text(100, 120 + tmp * 40, mFont, mLine,
					HorizontalAlign.CENTER);
			x = this.smoothCamera.getWidth() / 2.0f - highScoreText.getWidth()
					/ 2.0f;
			y = this.smoothCamera.getHeight() / 2.0f
					- highScoreText.getHeight() / 2.0f;

			while (mLine != null) {
				tmp++;
				final Text playerScore = new Text(100, 120 + tmp * 40,
						Fonts.getInstance().menuItemFont, mLine,
						HorizontalAlign.CENTER);
				// increase the y-axis for every player. Max 5 players!
				playerScore.setPosition(x, (int) (y * (0.35 + tmp * 0.3)));

				mLine = reader.readLine();

				this.attachChild(playerScore);

			}

		}catch (Exception e) {
			MyDebug.e("could not load high score file",  e);
		}
	}

	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}

}

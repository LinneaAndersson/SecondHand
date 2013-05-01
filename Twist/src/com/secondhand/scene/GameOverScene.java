package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.text.Text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;

import com.secondhand.controller.SceneManager;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;

/*
 * To Linnea: You said you didn't want to work on this class anymore, so I will be doing 
 * the work on this class from now on. Please do not disturb me. 
 * 
 * - Eric
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class GameOverScene extends GameScene {

	private Player player;
	Text textGameOver = null;
	private GameWorld gameWorld;

	public void setGameWorld(final GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public GameOverScene(final Engine engine, final Context context) {
		super(engine, context);
		MyDebug.d("creating the GameOverScene");	
	}

	@SuppressLint("NewApi")
	@Override
	public void loadScene() {
		super.loadScene();
		setGameWorld(SceneManager.getInstance().getGamePlayScene().getGameWorld());
		player = gameWorld.getPlayer();

// handle this before going to this scene.
//		if (HighScoreList.getInstance().madeItToHighScoreList(player.getScore()));
	}

	/*public void setHeadLine(final String text) {
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
	}*/

	@Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		if (pKeyCode == KeyEvent.KEYCODE_BACK
				&& pEvent.getAction() == KeyEvent.ACTION_DOWN) {
			// ignore the back button. 
			return true;
		} else {
			return false;
		}
	}

	@Override
	public AllScenes getParentScene() {
		// TODO Auto-generated method stub
		return null;
	}
}

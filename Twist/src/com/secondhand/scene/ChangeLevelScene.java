package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.resource.Fonts;
import com.secondhand.resource.LocalizationStrings;

import android.content.Context;

public class ChangeLevelScene extends GameScene{
	private static final float SECONDS_FOR_SCENE = 1f;
	private float secondsPassedSinceLastUpdate=0;
	private Font mFont;
	private GameWorld gameWorld;

	
	public ChangeLevelScene(Engine engine, Context context,GameWorld gameWorld) {
		super(engine, context);
		this.gameWorld=gameWorld;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadScene(){
		super.loadScene();
		MyDebug.d("loading changeLevelScene!");
		this.setBackground(new ColorBackground(0,0,0));
		MyDebug.d("loading changeLevelScene!");
		int levelNumber=gameWorld.getLevelNumber();
		MyDebug.d("loading changeLevelScene!");
		mFont = Fonts.getInstance().menuItemFont;
		MyDebug.d("loading changeLevelScene!");

		final Text levelName = new Text(100, 60, mFont, "Level" + gameWorld.getLevelNumber(),
				HorizontalAlign.CENTER);
		float x = this.smoothCamera.getWidth() / 2.0f - levelName.getWidth()
				/ 2.0f;
		float y = this.smoothCamera.getHeight() / 2.0f - levelName.getHeight()
				/ 2.0f;
		levelName.setPosition(x,y);
	
		this.attachChild(levelName);

	}
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {

		secondsPassedSinceLastUpdate += pSecondsElapsed;

		
		if (secondsPassedSinceLastUpdate >= SECONDS_FOR_SCENE) {
			secondsPassedSinceLastUpdate = 0;
			MyDebug.d("change scene to gameplayScene");
			setScene(AllScenes.GAME_PLAY_SCENE);
		}

		super.onManagedUpdate(pSecondsElapsed);
	}


	@Override
	public AllScenes getParentScene() {
		// TODO Auto-generated method stub
		return AllScenes.MAIN_MENU_SCENE;
	}

}

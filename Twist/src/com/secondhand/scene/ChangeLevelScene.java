package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;

import android.content.Context;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.GameWorld;
import com.secondhand.resource.Fonts;

public class ChangeLevelScene extends GameScene{
	private static final float SECONDS_FOR_SCENE = 4f;
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
		MyDebug.d("Loading ChangeLevelScene");
		this.setBackground(new ColorBackground(0,0,0));
		MyDebug.d("Loading ChangeLevelScene");
		int levelNumber=gameWorld.getLevelNumber();
		MyDebug.d("Loading ChangeLevelScene");
		mFont = Fonts.getInstance().menuItemFont;
		
		// The text is outside the camera, but I dont know how to fix...
		// TODO: Fix the position of the text!
		final Text levelName = new Text(300, 300, mFont, "Level" + gameWorld.getLevelNumber(),
				HorizontalAlign.CENTER);
		float x = this.smoothCamera.getWidth() / 2.0f - levelName.getWidth()
				/ 2.0f;
		float y = this.smoothCamera.getHeight() / 2.0f - levelName.getHeight()
				/ 2.0f;
		  levelName.setPosition(x,y);
		levelName.setScale(2);
		MyDebug.d("Loading ChangeLevelScene");
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

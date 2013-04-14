package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.text.Text;

import android.content.Context;

import com.secondhand.twirl.GlobalResources;

public class HighScoreScene extends GameScene {

	public HighScoreScene(Engine engine, Context context) {
		super(engine, context);
	}

	@Override
	public void loadResources() {
	}

	@Override
	public void loadScene() {
		// create the high score table here.
		// the rest is for Linnea to implement. 
		
		// add a test string
		Text highScoreText = new Text(0, 0, GlobalResources.getInstance().menuItemFont, "Welcome to high score!");
		
		// center the text both horizontally and vertically. 
		float x = this.camera.getWidth() / 2.0f - highScoreText.getWidth() / 2.0f;
		float y = this.camera.getHeight() / 2.0f - highScoreText.getHeight() / 2.0f;
		highScoreText.setPosition(x, y);
		
		this.attachChild(highScoreText);
		
	}


}

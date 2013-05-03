package com.secondhand.controller;

import org.anddev.andengine.entity.Entity;

import com.secondhand.view.scene.AllScenes;
import com.secondhand.view.scene.HighScoreScene;

public class HighScoreSceneController extends Entity {

	private float timer;
	
	public static final float SECONDS_PER_HIGH_SCORE_ENTRY = 0.5f;
	
	private final HighScoreScene highScoreScene;
	
	private final SceneController sceneController;
	
	public HighScoreSceneController(final HighScoreScene highScoreScene, final SceneController sceneController) {
		super();
		this.highScoreScene = highScoreScene;
		highScoreScene.attachChild(this);
		timer = 0;
		this.sceneController = sceneController; 
	}
	
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
			
		if(this.highScoreScene.isEntireHighScoreListShown()) {
			this.sceneController.switchScene(AllScenes.MAIN_MENU_SCENE);
			return;
		}
		
		timer += pSecondsElapsed;
		
		if(timer > SECONDS_PER_HIGH_SCORE_ENTRY) {
			
			timer = 0;
			
			this.highScoreScene.showNextHighScoreEntry();
		}

	}
	
	
}

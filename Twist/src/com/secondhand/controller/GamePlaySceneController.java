package com.secondhand.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.input.touch.TouchEvent;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.entity.PowerUpList;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.view.resource.HighScoreList;
import com.secondhand.view.scene.AllScenes;
import com.secondhand.view.scene.GamePlayScene;

final class GamePlaySceneController extends Entity implements PropertyChangeListener {

	private final IGameWorld gameWorld;
	private final SceneController sceneController;

	public GamePlaySceneController(final GamePlayScene scene,
			final SceneController sceneController) {
		super();
		
		final GamePlayScene gamePlayScene = scene;
		this.sceneController = sceneController;

		gameWorld = scene.getGameWorld();

		gamePlayScene.attachChild(this);

		//PlayerUtil adds this Controller as a listener
		this.gameWorld.getPlayer().addListener(this);
		PowerUpList.getInstance().addListener(this);
		
		scene.setOnSceneTouchListener(new GameSceneTouchListener());

		gameWorld.getPhysics().setContactListener();
	}

	private class GameSceneTouchListener implements IOnSceneTouchListener {
		@Override
		public boolean onSceneTouchEvent(final Scene pScene,
				final TouchEvent pSceneTouchEvent) {
			if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
				final float posX = pSceneTouchEvent.getX();
				final float posY = pSceneTouchEvent.getY();
				gameWorld.updateWithTouchInput(new Vector2(posX, posY));
				return true;
			}
			return false;
		}
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		if (gameWorld.isGameOver()) {

			if (!HighScoreList.getInstance().madeItToHighScoreList(
					gameWorld.getPlayer().getScore())) {
				// go to high score.
				this.sceneController.switchScene(AllScenes.HIGH_SCORE_SCENE);
			}

			if (InputDialogManager.input != null) {

				final HighScoreList.Entry newEntry = new HighScoreList.Entry(
						InputDialogManager.input, gameWorld.getPlayer()
						.getScore());
				HighScoreList.getInstance().insertInHighScoreList(newEntry);
				InputDialogManager.showing = false;

				InputDialogManager.input = null;

				this.sceneController.switchScene(AllScenes.HIGH_SCORE_SCENE);

			} else if (HighScoreList.getInstance().madeItToHighScoreList(
					gameWorld.getPlayer().getScore())) {

				InputDialogManager.showing = true;
				InputDialogManager.getInstance().showDialog();

			}

		} else {
			gameWorld.updateGameWorld();
		}
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		final String name = event.getPropertyName();
		MyDebug.d("property change in controller outside + name= " + name);
		
		if (name.equals(PowerUpList.ADD_POWERUP)) {
			MyDebug.d("property change in controller");
			this.sceneController.getSceneManager().registerUpdateHander(TimerFactory.createTimer((PowerUp)event.getNewValue()));
		}
	}
}

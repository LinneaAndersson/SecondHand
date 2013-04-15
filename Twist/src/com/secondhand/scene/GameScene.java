package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;

import com.secondhand.controller.IGameScene;
import com.secondhand.controller.SceneManager;
import com.secondhand.controller.SceneManager.AllScenes;

import android.content.Context;
import android.view.KeyEvent;

/**
 * Credit for the main idea behind this class goes to:
 * http://andengine.wikidot.com
 * /loading-resources-in-the-background-with-a-loading-screen
 */
public abstract class GameScene extends Scene implements IGameScene {

	protected final Camera camera;
	protected final Engine engine;
	protected final Context context;

	public GameScene(Engine engine, Context context) {
		super();
		this.camera = engine.getCamera();
		this.engine = engine;
		this.context = context;
	}

	@Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		if (pKeyCode == KeyEvent.KEYCODE_BACK
				&& pEvent.getAction() == KeyEvent.ACTION_DOWN) {
			AllScenes parent = getParentScene();
			if (parent != null)
				// TODO needs to save game and have a continue option on the
				// menuScene if the current scene is gamePlayScene
				SceneManager.getInstance().setCurrentSceneEnum(parent);
			else
				return false;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Scene getScene() {
		return this;
	}

	public abstract AllScenes getParentScene();
}

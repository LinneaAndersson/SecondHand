package com.secondhand.controller;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.input.touch.TouchEvent;

import com.secondhand.scene.GamePlayScene;

public class GamePlaySceneController {

	private class GameSceneTouchListener implements IOnSceneTouchListener{
		@Override
		public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {	
			return false;
		}
	}
}

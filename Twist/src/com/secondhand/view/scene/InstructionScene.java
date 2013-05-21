package com.secondhand.view.scene;

import org.anddev.andengine.engine.Engine;

import android.content.Context;


public class InstructionScene extends GameScene{

	public InstructionScene(Engine engine, Context context) {
		super(engine, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AllScenes getParentScene() {
		// TODO Auto-generated method stub
		return AllScenes.MAIN_MENU_SCENE;
	}

}

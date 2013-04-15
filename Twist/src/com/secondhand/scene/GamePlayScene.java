package com.secondhand.scene;

import java.util.List;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;

import android.content.Context;

import com.secondhand.controller.SceneManager.AllScenes;

public class GamePlayScene extends GameScene {
	
	private List<IShape> shapeList;
	private IShape player;
	
	public GamePlayScene(Engine engine, Context context) {
		super(engine, context);
	}
	
	public void setShapes(List<IShape> list){
		shapeList = list;
	}
	
	public void setPlayer(IShape player){
		this.player = player;
	}

	@Override
	public void loadResources() {
		// load the resources of this scene
	}

	@Override
	public void loadScene() {
		// now load the scene(attach all the entities)
		
		attachChild(player);
		/*for(IShape s : shapeList){
			attachChild(s);
		}*/
		engine.getCamera().setChaseEntity(player);
		
		setBackground(new ColorBackground(50f, 0f, 0f));
	}
	
	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}
}

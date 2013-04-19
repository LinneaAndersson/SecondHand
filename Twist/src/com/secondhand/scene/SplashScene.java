package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.modifier.MoveXModifier;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;

import android.content.Context;

import com.secondhand.twirl.GlobalResources;


public class SplashScene extends GameScene {
	private final Text title1;
	private final Text title2;
	
	public SplashScene(Engine engine, Context context){
		super(engine, context);
		
		setBackground(new ColorBackground(0,0,0));
		
		Font font = GlobalResources.getInstance().menuItemFont;
				
		title1 = new Text(0, 0, font, "Second");
		title2 = new Text(0, 0, font, "Hand");
		
		title1.setPosition(-title1.getWidth(), engine.getCamera().getHeight() / 2);
		title2.setPosition(engine.getCamera().getWidth(),
				engine.getCamera().getHeight() / 2);
		
		attachChild(title1);
		attachChild(title2);

		title1.registerEntityModifier(new MoveXModifier(1, title1.getX(),
				engine.getCamera().getWidth() / 2 - title1.getWidth()));
		title2.registerEntityModifier(new MoveXModifier(1, title2.getX(),
				engine.getCamera().getWidth() / 2));
	}

	@Override
	public void loadResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AllScenes getParentScene() {
		// TODO Auto-generated method stub
		return null;
	}
}

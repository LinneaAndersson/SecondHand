package com.secondhand.view.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;

import com.secondhand.view.resource.Fonts;
import com.secondhand.view.resource.LocalizationStrings;

import android.content.Context;

public class InstructionScene extends GameScene {

	public InstructionScene(Engine engine, Context context) {
		super(engine, context);
		// TODO Auto-generated constructor stub
	}

	public void loadScene() {
		super.loadScene();
		
		final Font mFont = Fonts.getInstance().menuItemFont;
		
		final Text instructions = new Text(100, 60, mFont, LocalizationStrings
				.getInstance().getLocalizedString("menu_instructions"),
				HorizontalAlign.CENTER);
		instructions.setScale(1.5f);

		this.attachChild(instructions);

	}

	@Override
	public AllScenes getParentScene() {
		// TODO Auto-generated method stub
		return AllScenes.MAIN_MENU_SCENE;
	}

}

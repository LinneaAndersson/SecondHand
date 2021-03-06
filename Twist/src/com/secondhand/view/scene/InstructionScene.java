package com.secondhand.view.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;

import android.content.Context;

import com.secondhand.view.resource.Fonts;
import com.secondhand.view.resource.LocalizationStrings;

public class InstructionScene extends GameScene {

	public InstructionScene(final Engine engine, final Context context) {
		super(engine, context);
	}

	public void loadScene() {
		super.loadScene();

		final Font mFont = Fonts.getInstance().menuItemFont;
		final Text instructions = new Text(100, 60, mFont, LocalizationStrings
				.getInstance().getLocalizedString("menu_instructions"),
				HorizontalAlign.CENTER);
		final Text theInstruction = new Text(100, 150, mFont,
				LocalizationStrings.getInstance().getLocalizedString(
						"game_instructions"));

		instructions.setScale(1.5f);

		this.attachChild(instructions);
		this.attachChild(theInstruction);

	}

	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}

}

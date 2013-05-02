package com.secondhand.view.scene;

import java.util.Arrays;

import org.anddev.andengine.entity.text.ChangeableText;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.view.resource.Fonts;

// This will be between the Levels. 
public final class NewLevelText extends ChangeableText {

	private static final float SECONDS_FOR_SCENE = 4f;
	private float secondsPassedSinceLastUpdate=0; 
	private int level;


	public static String getString(int level) {
		if (level > 0) {
			return "Level: " + level;
		} else {
			return "";
		}
	}

	public NewLevelText(final Vector2 position, final int level) {
		super(position.x, position.y, Fonts.getInstance().menuItemFont,
				"0");

		this.level = level;

		setText(getString(level));
	}

	public void setLevelBegin(final int newLevel) {
		setText(getString(newLevel));
		this.level = newLevel;
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		
		secondsPassedSinceLastUpdate += pSecondsElapsed;


		if (secondsPassedSinceLastUpdate >= SECONDS_FOR_SCENE) {
			secondsPassedSinceLastUpdate = 0;
			this.setLevelBegin(0);
		} else {
			super.onManagedUpdate(pSecondsElapsed);
		
		}
	}

}

package com.secondhand.scene;

import org.anddev.andengine.entity.text.ChangeableText;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.resource.Fonts;

// used to show the score and lives in the HUD.
public class ScoreLivesText extends ChangeableText {

	public ScoreLivesText(Vector2 position) {
		super(position.x, position.y, Fonts.getInstance().menuItemFont,
				"Score: 10000  Lives: 3");

	}
	
}

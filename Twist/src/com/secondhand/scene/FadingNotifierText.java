package com.secondhand.scene;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.modifier.AlphaModifier;
import org.anddev.andengine.entity.modifier.MoveYModifier;
import org.anddev.andengine.entity.text.Text;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.resource.Fonts;

public class FadingNotifierText extends Text {

	private static final float DURATION = 1.7f;
	
	
	FadingNotifierText(final String str, final Vector2 position) {
		super(position.x, position.y, Fonts.getInstance().menuItemFont, str);
		
		// otherwise the alpha channel won't work
		setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		
		registerEntityModifier(new AlphaModifier(DURATION, 1, 0));
		registerEntityModifier(new MoveYModifier(DURATION, getY(), getY() - 50));
	}
	
}

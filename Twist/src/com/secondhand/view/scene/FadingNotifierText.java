package com.secondhand.view.scene;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.AlphaModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.MoveYModifier;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.util.modifier.IModifier;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.view.resource.Fonts;

public class FadingNotifierText extends Text {

	private static final float DURATION = 1.7f;
	
	
	FadingNotifierText(final String str, final Vector2 position) {
		super(position.x, position.y, Fonts.getInstance().menuItemFont, str);
		
		// otherwise the alpha channel won't work
		setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		
		final IEntityModifierListener modifierListener= new IEntityModifierListener() {

			@Override
			public void onModifierStarted(final IModifier<IEntity> pModifier,
				final IEntity pItem) {

			}

			@Override
			public void onModifierFinished(final IModifier<IEntity> pModifier,
					final IEntity pItem) {
				// clean up
				FadingNotifierText.this.detachSelf();
			}    	
		};
		
		registerEntityModifier(new AlphaModifier(DURATION, 1, 0, modifierListener));
		registerEntityModifier(new MoveYModifier(DURATION, getY(), getY() - 50));
	}
	
}

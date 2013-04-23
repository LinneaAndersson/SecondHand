package com.secondhand.controller;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.text.ChangeableText;

import com.secondhand.resource.Fonts;
import com.secondhand.resource.LocalizationStrings;

/*
 * Implements a loading animation that looks like this:
 "Loading."
 "Loading.."
 "Loading..."
 */
public class LoadingText extends ChangeableText {

	// how many seconds to wait before showing the next string in the
	// "animation"
	private static final float SECONDS_PER_STRING = 0.3f;

	private String[] loadingStrings;
	private int loadingStringsI;

	private float secondsPassedSinceLastUpdate;

	public LoadingText(final Camera camera) {
		super(0, 0, Fonts.getInstance().menuItemFont,
		// TODO: ugly hack, fix(this string has to be longer than the presented
		// string for some reason)
				"frferffreferloading");

		this.setText(getNextString());

		// center the "loading" text both horizontally and vertically.
		final float posX = camera.getWidth() / 2.0f - this.getWidth() / 2.0f;
		final float posY = camera.getHeight() / 2.0f - this.getHeight() / 2.0f;
		this.setPosition(posX, posY);
	}

	// get next string in the animation
	private String getNextString() {
		if (loadingStrings == null) {
			// create the loading strings.

			final String loadingString = LocalizationStrings.getInstance()
					.getLocalizedString("loading");
			loadingStrings = new String[] { loadingString + ".",
					loadingString + "..", loadingString + "..." };

			// start the animation:
			loadingStringsI = -1;
		}

		// the "%" makes it loop.
		loadingStringsI = (loadingStringsI + 1) % loadingStrings.length;
		return loadingStrings[loadingStringsI];
	}
	
	// to Johan. a method like this could be used to manage powerUps. 
	// gamePlayScene would probably be the best place to control them.
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {

		// MyDebug.d("seconds passed: " + pSecondsElapsed);

		secondsPassedSinceLastUpdate += pSecondsElapsed;

		if (secondsPassedSinceLastUpdate >= SECONDS_PER_STRING) {
			secondsPassedSinceLastUpdate = 0;
			final String next = getNextString();
			this.setText(next);
		}

		super.onManagedUpdate(pSecondsElapsed);
	}

}

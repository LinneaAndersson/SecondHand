package com.secondhand.controller;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.text.ChangeableText;

import com.secondhand.model.LocalizationStrings;
import com.secondhand.twirl.GlobalResources;

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

	private String[] loadingStrings = null;
	private int loadingStringsI;

	private float secondsPassedSinceLastUpdate = 0;

	public LoadingText(Camera camera) {
		super(0, 0, GlobalResources.getInstance().menuItemFont,
		// TODO: ugly hack, fix(this string has to be longer than the presented
		// string for some reason)
				"frferffreferloading");

		this.setText(getNextString());

		// center the "loading" text both horizontally and vertically.
		float x = camera.getWidth() / 2.0f - this.getWidth() / 2.0f;
		float y = camera.getHeight() / 2.0f - this.getHeight() / 2.0f;
		this.setPosition(x, y);
	}

	// get next string in the animation
	private String getNextString() {
		if (loadingStrings == null) {
			// create the loading strings.

			String loadingString = LocalizationStrings.getInstance()
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

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {

		// MyDebug.d("seconds passed: " + pSecondsElapsed);

		secondsPassedSinceLastUpdate += pSecondsElapsed;

		if (secondsPassedSinceLastUpdate >= SECONDS_PER_STRING) {
			secondsPassedSinceLastUpdate = 0;
			String next = getNextString();
			this.setText(next);
		}

		super.onManagedUpdate(pSecondsElapsed);
	}

}

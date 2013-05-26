package com.secondhand.view.andengine.entity;

import java.util.Arrays;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.text.ChangeableText;

import com.secondhand.view.resource.Fonts;

/*
 * Implements a loading animation that looks like this:
 "Loading."
 "Loading.."
 "Loading..."
 */
public class LoadingTextView extends ChangeableText {


	private String[] loadingStrings;
	private int loadingStringsI;
	private final Camera camera;

	private static String getPlaceHolderString() {
		// a length of 20 should be good enough.
		final char[] exmarks = new char[20];
		Arrays.fill(exmarks, ' ');
		return new String(exmarks);
	}	

	public LoadingTextView( final Camera camera) {
		super(0, 0, Fonts.getInstance().menuItemFont,
				getPlaceHolderString());
		this.camera = camera;		
	}
	
	private void createLoadingStrings() {
		// create the loading strings.

		loadingStrings = new String[] { this.getText() + ".",
				this.getText() + "..", this.getText() + "..." };
	}

	// get next string in the animation
	private String getNextString() {
		if (loadingStrings == null) {
			createLoadingStrings();
		}

		// the "%" makes it loop.
		loadingStringsI = (loadingStringsI + 1) % loadingStrings.length;
		return loadingStrings[loadingStringsI];
	}
	
	public void startAnimation(final String loadingString) {
		
		this.setText(loadingString);
		// center the "loading" text both horizontally and vertically.
		final float posX = camera.getWidth() / 2.0f - this.getWidth() / 2.0f;
		final float posY = camera.getHeight() / 2.0f - this.getHeight() / 2.0f;
		this.setPosition(posX, posY);
		
		createLoadingStrings();
		
		// start the animation:
		loadingStringsI = -1;
		this.setText(getNextString());
	}
	
	public void goToNextStringInAnimation() {
		this.setText(getNextString());
	
	}
}


package com.secondhand.controller;

import org.anddev.andengine.entity.Entity;

import com.secondhand.view.entity.LoadingTextView;

public class LoadingTextController extends Entity {
	
	// how many seconds to wait before showing the next string in the
	// "animation"
	private static final float SECONDS_PER_STRING = 0.3f;

	
	private float secondsPassedSinceLastUpdate;

	private final LoadingTextView view;
	
	public LoadingTextController(final LoadingTextView view) {
		super();
		this.view = view;
	}
	
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {

//		MyDebug.d("seconds passed: " + pSecondsElapsed);

		secondsPassedSinceLastUpdate += pSecondsElapsed;


		if (secondsPassedSinceLastUpdate >= SECONDS_PER_STRING) {
			secondsPassedSinceLastUpdate = 0;
			this.view.goToNextStringInAnimation();
		}

		super.onManagedUpdate(pSecondsElapsed);
	}
}

package com.secondhand.controller;

import org.anddev.andengine.engine.camera.SmoothCamera;

public class MySmoothCamera extends SmoothCamera {

	public MySmoothCamera(final float pX, final float pY, final float pWidth, final float pHeight,
			final float pMaxVelocityX, final float pMaxVelocityY, final float pMaxZoomFactorChange) {
		super(pX, pY, pWidth, pHeight, pMaxVelocityX, pMaxVelocityY,
				pMaxZoomFactorChange);
	}
	
	// Please excuse the swearing; it's just that AndEngine can really piss you off at times.
	// what's the point of this class?
	public void setCenterDirectThatActuallyFuckingWorks(final float pCenterX, final float pCenterY) {
		super.setCenter(pCenterX, pCenterY);
	}
}

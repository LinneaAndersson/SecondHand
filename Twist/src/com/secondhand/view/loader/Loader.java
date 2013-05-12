package com.secondhand.view.loader;

import org.anddev.andengine.engine.Engine;

import android.content.Context;

public class Loader {
	
	protected Engine engine;
	protected Context context;
		
	protected Loader() { }

	public void initialize(final Context context, final Engine engine) {
		this.context = context;
		this.engine = engine;
		
	}
}

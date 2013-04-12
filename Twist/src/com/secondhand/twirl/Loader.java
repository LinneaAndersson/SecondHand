package com.secondhand.twirl;

import org.anddev.andengine.engine.Engine;


import android.content.Context;

public abstract class Loader implements ILoader {
	
	protected Engine engine;
	protected Context context;
		
	protected Loader() {
		setAssetBasePath(getDefaultAssetBasePath());
	}
	

	public void initialize(final Context context, final Engine engine) {
		this.context = context;
		this.engine = engine;
		
	}
}

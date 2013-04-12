package com.secondhand.twirl;

import org.anddev.andengine.engine.Engine;

import android.content.Context;

public interface ILoader {

	public void setAssetBasePath(final String path);
	
	public String getDefaultAssetBasePath();
	
	public void initialize(final Context context, final Engine engine);
}

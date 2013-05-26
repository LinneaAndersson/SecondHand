package com.secondhand.view.resource;

import android.content.Context;

/*
 * Singelton class for accessing the localization strings. 
 */
public final class LocalizationStrings {

	private static LocalizationStrings instance = null;

	private Context context;

	private LocalizationStrings() {
	}

	public static LocalizationStrings getInstance() {
		if (instance == null) {
			instance = new LocalizationStrings();
		}
		return instance;
	}

	public void initialize(final Context context) {
		this.context = context;
	}

	public String getLocalizedString(final String stringName) {
		final String packageName = context.getPackageName();
		final int resId = context.getResources().getIdentifier(stringName,
				"string", packageName);
		return context.getString(resId);
	}

}

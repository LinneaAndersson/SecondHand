package com.secondhand.model;

import java.util.List;

import android.content.Context;

/**
 * Singelton class for accessing the localization strings. 
 */
public class LocalizationStrings {

	private static LocalizationStrings instance = null;

	private Context context;

	private LocalizationStrings() {}

	public static LocalizationStrings getInstance() {
		if(instance == null) {
			instance = new LocalizationStrings();
		}
		return instance;
	}



	public void initialize(Context context) {
		this.context = context;
	}

	public String getLocalizedString(String stringName) {
		String packageName = context.getPackageName();
		int resId = context.getResources().getIdentifier(stringName, "string", packageName);
		return context.getString(resId);
	}

	public String[] getLocalizedStringArray(String stringName){
		String packageName = context.getPackageName();
		int resId = context.getResources().getIdentifier(stringName, "string", packageName);
		return context.getResources().getStringArray(resId);
	}

	public int[] getLocalizedIntArray(String stringName) {
		String packageName = context.getPackageName();
		int resId = context.getResources().getIdentifier(stringName, "string", packageName);
		return context.getResources().getIntArray(resId);

	}
}

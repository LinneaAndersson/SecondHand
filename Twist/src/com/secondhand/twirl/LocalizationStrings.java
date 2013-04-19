package com.secondhand.twirl;


import android.content.Context;

/**
 * Singelton class for accessing the localization strings. 
 */
public final class LocalizationStrings {

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
	
	// Don't know if this is correct
	public String[] getLocalizedStringArray(String stringName){
		String packageName = context.getPackageName();
		int resId = context.getResources().getIdentifier(stringName, "string", packageName);
		return context.getResources().getStringArray(resId);
	}
	
	// Don't know if this is correct
	public int[] getLocalizedIntArray(String stringName){
		String packageName = context.getPackageName();
		int resId = context.getResources().getIdentifier(stringName, "string", packageName);
		return context.getResources().getIntArray(resId);
	}
}

package com.secondhand.twirl;

import java.util.Locale;

import com.secondhand.debug.MyDebug;

import android.content.Context;

/**
 * Singelton class for accessing the localization strings. 
 */
public class LocalizationStrings {

	private static LocalizationStrings instance = null;
	
	
	public static LocalizationStrings getInstance() {
		if(instance == null) {
			instance = new LocalizationStrings();
		}
		return instance;
	}
	
	private LocalizationStrings() {}
	
	private Context context;
	
	public void initialize(Context context) {
		this.context = context;
	}
	
	public String getLocalizedString(String stringName) {
	      String packageName = context.getPackageName();
	      int resId = context.getResources().getIdentifier(stringName, "string", packageName);
	      return context.getString(resId);
	}
}

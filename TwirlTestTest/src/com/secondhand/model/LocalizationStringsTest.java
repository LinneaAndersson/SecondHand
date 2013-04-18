package com.secondhand.model;

import com.secondhand.debug.MyDebug;

import junit.framework.TestCase;
import android.content.Context;

public class LocalizationStringsTest extends TestCase{

	public void testConstructor() {
		
	}

	public void testGetLocaliztionStringArray() {
		MyDebug.i( LocalizationStrings.getInstance().getLocalizedIntArray(
				"high_score")[0] + "");
		int[] str = LocalizationStrings.getInstance().getLocalizedIntArray(
				"high_score");
		assertEquals(str[1],3210);
	}

	public void testGetLocalizationIntArray() {
		String[] str = LocalizationStrings.getInstance().getLocalizedStringArray(
				"high_score_list");
		assertEquals(str[1],3210);
	}
}

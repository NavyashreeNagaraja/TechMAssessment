package com.amazon.parentdriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ParentDriver {
	public static AndroidDriver<AndroidElement> driver;
	
	public ParentDriver(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	public static String Entered_ItemName; 
	public static String ItemName_BeforeAddingToCart;
}

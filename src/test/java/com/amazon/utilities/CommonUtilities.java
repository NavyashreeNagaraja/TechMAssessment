package com.amazon.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CommonUtilities {
	
	public static AndroidDriver driver;
	public static long IMPLICIT_WAIT;
	private static String DEVICE_NAME;
	private static String PLATFORM_NAME;
	private static String PLATFORM_VERSION;
	private static String APP_PACKAGE;
	private static String APP_ACTIVITY;
	private static String APPIUM_PORT_NUMBER;
	private static String APP;
	public static URL serverUrl;
	public static DesiredCapabilities capabilities = new DesiredCapabilities();
	
	public static void loadConfig(String fileName ) throws IOException {
	
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/"+fileName);
		Properties pro = new Properties();
		pro.load(fis);
		
		IMPLICIT_WAIT = (long)Integer.parseInt(pro.getProperty("implicit.wait"));
		DEVICE_NAME = pro.getProperty("device.name");
		PLATFORM_NAME = pro.getProperty("platform.name");
		PLATFORM_VERSION = pro.getProperty("platform.version");
		APP_PACKAGE = pro.getProperty("base.pkg");
		APP_ACTIVITY = pro.getProperty("activity.pkg");
		APPIUM_PORT_NUMBER = pro.getProperty("appium.port.number");
		APP = pro.getProperty("app.name");
	}
	
	public static void loadCapabilities() {
		
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
		capabilities.setCapability("app", APP);
		//capabilities.setCapability("appPackage", APP_PACKAGE);
		//capabilities.setCapability("appActivity", APP_ACTIVITY);
		
	}
	
	public static AndroidDriver getDriver() throws IOException {
		
		serverUrl=new URL("http://0.0.0.0:"+APPIUM_PORT_NUMBER+"/wd/hub");
		driver=new AndroidDriver(serverUrl,capabilities);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		return driver;
		
	}

}

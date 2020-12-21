package com.amazon.pages;

import org.openqa.selenium.support.PageFactory;

import com.amazon.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends ParentDriver 
{
	public HomePage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}	

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private AndroidElement AlreadyACustomer_SignIn_Button;

	
	public WelcomePage SelectAlreadyACustomerButton(){
		AlreadyACustomer_SignIn_Button.click();
		return new WelcomePage(driver);
	}
}

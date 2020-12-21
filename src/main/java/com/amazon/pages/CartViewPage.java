package com.amazon.pages;

import org.openqa.selenium.support.PageFactory;

import com.amazon.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.testng.Assert;
import org.testng.annotations.*;
public class CartViewPage extends ParentDriver 
{
	public CartViewPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	} 
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Proceed to Buy\")")
	AndroidElement ProceedToBuy_Button;
	
	@AndroidFindBy(xpath = "//android.widget.ListView/android.view.View[1]")
	AndroidElement Price_InCartView;
	
	public String getPrice_In_CartView() 
	{
		System.out.println("Item price in cart view is : " + Price_InCartView.getText());
		return Price_InCartView.getText();
	}
	public String getItemName_In_CartView() 
	{
		String ItemName_InCartView = "";
		System.out.println("Entered_ItemName:"+Entered_ItemName);
		if (driver.findElementsByXPath("//android.widget.TextView[contains(@text,\""+ Entered_ItemName + "\")]").size() > 0)
		{
			ItemName_InCartView= driver.findElementByXPath("//android.widget.TextView[contains(@text,\""+ Entered_ItemName + "\")]").getText();
		}
		else
		{
			Assert.fail("Item name is not visible in Cart View Page");
		}
		return ItemName_InCartView.replaceAll(".", "");
	}
	public void Select_ProceedToBuy() 
	{
		ProceedToBuy_Button.click();
	}
}

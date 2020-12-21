package com.amazon.pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ItemsDisplayPage extends ParentDriver 
{
	public static String Price_BeforeAddingToCart;

	public ItemsDisplayPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Add to Cart\")")
	AndroidElement AddToCart_Button;
						
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"rupees\")")
	AndroidElement ItemPrice;

	@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image")
	AndroidElement GoToCart_Image;

	public String getItemPrice() throws InterruptedException 
	{
		String Price_BeforeAddingToCart;
		//Trying to fetch the Item price before adding to the cart
		Price_BeforeAddingToCart = ItemPrice.getText();			
		return Price_BeforeAddingToCart.replace("rupees", "");
	}
	public String getItemName_BeforeAddingToCart(String ItemName) 
	{
		if (driver.findElementsByXPath("//android.view.View[contains(@text,\""+ ItemName + "\")]").size() > 0) 
		{
			//Trying to fetch the Item name before adding to the cart
			ItemName_BeforeAddingToCart= driver.findElementByXPath("//android.view.View[contains(@text,\"" + ItemName+ "\")]").getText();
		}
		return ItemName_BeforeAddingToCart;
	}
	
	public CartViewPage GoToCart() 
	{
		//scrolling until it finds the text "Add to cart"
		this.driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+ "new UiSelector().textContains(\"Add to Cart\"));");
		
		AddToCart_Button.click();
		String ProceedToCheckout = "Proceed to checkout";
		String Cart = "Cart";
		//If a screen pops up partially with 2 buttons named "Cart" and "Proceed to checkout", then the below code clicks on "cart" button
		if (driver.findElementsByXPath("//android.widget.Button[contains(@text,\""+ ProceedToCheckout + "\")]").size() > 0) 
		{
			driver.findElementByXPath("//android.widget.Button[contains(@text,\""+ Cart + "\")]").click();
		}
		else
		{
			GoToCart_Image.click();
		}
		
		return new CartViewPage(driver);
	}

}

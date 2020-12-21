package com.amazon.pages;

import org.openqa.selenium.support.PageFactory;

import com.amazon.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.awt.event.KeyEvent;
import java.util.List;

public class SearchItemPage extends ParentDriver 
{
	public SearchItemPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	} 
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Search\")")
	AndroidElement SearchItem_TextBox;
	
	@AndroidFindBy(className="android.widget.TextView")
	static List<AndroidElement> ItemList;
	
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/loc_ux_update_current_pin_code")
	private AndroidElement UseMyCurrentLocation_Button;
		
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_always_button")
	private AndroidElement AllowAlways_Button_InLocationPopup;
	
	public ItemsDisplayPage SearchItem(String ItemName) throws InterruptedException {
		Thread.sleep(10000);
		SearchItem_TextBox.click();
		//entering the Item name in the search product text box
		SearchItem_TextBox.sendKeys(ItemName.toLowerCase());
		Thread.sleep(5000);
		SelectItem();
		//selecting the "Use my current location" button 
		//UseMyCurrentLocation_Button.click();
		//selecting the "Allow all the time" button
		//AllowAlways_Button_InLocationPopup.click();
		//SelectItem();
		return new ItemsDisplayPage(driver);
	}
	public static void SelectItem()
	{
		for(int i=1; i<=ItemList.size(); i++)
		{
			String CurrentItemName = ItemList.get(i).getText();
			
			//The below loop helps us to not select the first product and the last product i.e.., it clicks any other product in the product list apart from first and last one.
			if(i!=1 && i!=ItemList.size())
			{
					ItemList.get(i).click();
					break;
			}
		}
	}
}

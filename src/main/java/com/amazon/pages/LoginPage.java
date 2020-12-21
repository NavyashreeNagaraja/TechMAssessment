package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amazon.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends ParentDriver{
	public LoginPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}	
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='ap_password']")
	private AndroidElement Password_TextBox;
												 
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='signInSubmit']")
	private AndroidElement Login_Button;

	String invalid_login_password = "123";
	
	public void VerifyFunctionality_WhenNoPasswordIsEntered()
	{
		//click on the Login button without entering any password
		Login_Button.click();
		boolean isEnterPassword_TextVisible = (this.driver.findElements(By.xpath("//android.view.View[@text='Enter your password']")).size()) == 1 ? true : false;
		if(isEnterPassword_TextVisible == true)
		{
			System.out.println("When user doesn't enter login password and clicks on Login button, then the text 'Enter your password' is visible");
		}
		else
		{
			Assert.fail("When user doesn't enter login password and clicks on Login button, then the text 'Enter your password' is NOT visible");
		}
	}
	public void VerifyFunctionality_WhenInvalidPasswordIsEntered()
	{
		Password_TextBox.sendKeys(invalid_login_password);
		Login_Button.click();
		boolean isInvalidPassword_TextVisible = (this.driver.findElements(By.xpath("//android.view.View[@text='Your password is incorrect']")).size()) == 1 ? true : false;
		if(isInvalidPassword_TextVisible == true)
		{
			System.out.println("When user entered invalid password, then the text 'Your password is incorrect' is visible on the screen");
		}
		else
		{
			Assert.fail("When user entered invalid password, then the text 'Your password is incorrect' is NOT visible on the screen");
		}
		
	}
	
	public SearchItemPage VerifyFunctionality_WhenValidPasswordIsEntered(String valid_login_password)
	{
		Password_TextBox.sendKeys(valid_login_password);
		
		Login_Button.click();
		return new SearchItemPage(driver);
	}
	
}

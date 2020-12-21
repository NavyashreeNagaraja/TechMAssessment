package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.amazon.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WelcomePage extends ParentDriver {
	public WelcomePage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Continue\")")
	AndroidElement Continue_Button;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='ap_email_login']")
	private AndroidElement EmailLogin_TextBox;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='ap_email_login']")
	private AndroidElement EmailLogin_TextBox_Highlighted;
		
	String invalid_login_emailid = "abczq@gmail.com";
	
	@AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.RelativeLayout")
	private AndroidElement ClearButton_InLogin_TextBox;
	
	public void VerifyFunctionality_WhenNoLoginEmailIsEntered()
	{
		//click on the continue button without entering any username or email id
		Continue_Button.click();
		//verifying whether "Enter email or mobile" text is getting displayed or not
		boolean isEnterEmailOrMobile_TextVisibile = (this.driver.findElements(By.xpath("//android.view.View[@text='Enter your email or mobile phone number']")).size())== 1 ? true : false;
		if(isEnterEmailOrMobile_TextVisibile == true)
		{
			System.out.println("When user doesn't enter login username and clicks on Continue button, the text : 'Enter username or mobile phone number' is visible on the screen");
		}
		else
		{
			Assert.fail("When user doesn't enter login username and clicks on Continue button, the text : 'Enter username or mobile phone number' is NOT visible on the screen");
		}
	}
	public void VerifyFunctionality_WhenInvalidEmailIsEntered()
	{
		EmailLogin_TextBox.sendKeys(invalid_login_emailid);
		Continue_Button.click();

		boolean isInvalidLoginID_TextVisible = (this.driver.findElements(By.xpath("//android.view.View[@text='We cannot find an account with that email address']"))
				.size())== 1 ? true : false;
		
		if(isInvalidLoginID_TextVisible == true)
		{
			System.out.println("When user entered invalid email id/username, then the text : 'We cannot find an account with that email address' is visible on the screen");
		}
		else
		{
			Assert.fail("When user entered invalid email id/username, then the text : 'We cannot find an account with that email address' is NOT visible on the screen");
		}
	}
	
	public LoginPage VerifyFunctionality_WhenValidLoginEmailIsEntered(String valid_login_emailid)
	{
		ClearButton_InLogin_TextBox.click();
		EmailLogin_TextBox_Highlighted.sendKeys(valid_login_emailid);
		Continue_Button.click();
		return new LoginPage(driver);
	}
	
}

package com.amazon.tests;

import java.io.IOException;
import java.net.URL;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.log4j.PropertyConfigurator;
import com.amazon.pages.*;
import com.amazon.pages.HomePage;
import com.amazon.parentdriver.ParentDriver;
import com.amazon.utilities.AppiumServer;
import com.amazon.utilities.CommonUtilities;
import com.amazon.utilities.ReadExcel;

import io.appium.java_client.android.AndroidDriver;

public class Test1 
{
	HomePage HomePage_Object;
	LoginPage LoginPage_Object;
	WelcomePage WelcomePage_Object;
	SearchItemPage SearchItemPage_Object;
	ItemsDisplayPage ItemsDisplayPage_Object;
	CartViewPage CartViewPage_Object;
	ParentDriver ParentDriver_Object;
	public static AndroidDriver driver;
	
	public static String loadPropertyFile = "amazon_Android.properties";
	public ReadExcel Excel_Object = new ReadExcel();
	String FilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData";
	public static Logger log = Logger.getLogger(Test1.class);
	
	@BeforeTest
	public void Setup()throws IOException
	{
		Excel_Object.readExcel(FilePath, "InputDataFile.xlsx", "InputSheet1");
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/test/resources/properties/logs4j.properties");
		AppiumServer.start();
		log.info("Appium Server Started !!");
		CommonUtilities.loadConfig(loadPropertyFile);
		log.info(loadPropertyFile + " properties file loaded !!!");
		CommonUtilities.loadCapabilities();
		driver=CommonUtilities.getDriver();

		HomePage_Object = new HomePage(driver);
		log.info("Test case execution started");
	}
	
	@Test(dataProvider="InputData")
	public void VerifyItemDataInCartView(String Item_Name,String Login_EmailId, String Login_Password) throws InterruptedException 
	{
		ParentDriver_Object.Entered_ItemName = Item_Name;
		
		WelcomePage_Object = HomePage_Object.SelectAlreadyACustomerButton();
		WelcomePage_Object.VerifyFunctionality_WhenNoLoginEmailIsEntered();
		WelcomePage_Object.VerifyFunctionality_WhenInvalidEmailIsEntered();
		
		LoginPage_Object = WelcomePage_Object.VerifyFunctionality_WhenValidLoginEmailIsEntered(Login_EmailId);
		
		LoginPage_Object.VerifyFunctionality_WhenNoPasswordIsEntered();
		LoginPage_Object.VerifyFunctionality_WhenInvalidPasswordIsEntered();
		SearchItemPage_Object = LoginPage_Object.VerifyFunctionality_WhenValidPasswordIsEntered(Login_Password);
		
		ItemsDisplayPage_Object = SearchItemPage_Object.SearchItem(Item_Name);
		
		String ItemName_BeforeAddingToCart= ItemsDisplayPage_Object.getItemName_BeforeAddingToCart(Item_Name);
		String Price_BeforeAddingToCart = ItemsDisplayPage_Object.getItemPrice();
		
		CartViewPage_Object = ItemsDisplayPage_Object.GoToCart();
		//CartViewPage_Object.Select_ProceedToBuy();
		
		String Price_InCartView = CartViewPage_Object.getPrice_In_CartView();
		System.out.println("Price_InCartView: " + Price_InCartView.trim());
		System.out.println("Price_BeforeAddingToCart: " + Price_BeforeAddingToCart.trim());
		
		//Verifying whether the Item price in the Cart View is same as the  Item Price before adding to cart.
		Assert.assertTrue(Price_InCartView.trim().contains(Price_BeforeAddingToCart.trim()));
		log.info("Item price in the Cart View is same as the  Item Price before adding to cart");
		
		String ItemName_InCartView= CartViewPage_Object.getItemName_In_CartView();
		
		System.out.println("ItemName_InCartView: " + ItemName_InCartView.trim());
		System.out.println("ItemName_BeforeAddingToCart: " + ItemName_BeforeAddingToCart.trim());
		//Verifying whether the Item Name in the Cart View is same as the Item Name before adding to cart.
		Assert.assertTrue(ItemName_BeforeAddingToCart.trim().contains(ItemName_InCartView.trim()));
		log.info("Item Name in the Cart View is same as the Item Name before adding to cart");
		
	}
	
	@DataProvider(name="InputData")
	public Object[][] getInputData() throws Exception 
	{	
		int TotalNumberOfRows = Excel_Object.getTotalNumberOfRows();
		int TotalNumberOfColumns = Excel_Object.getTotalNumberOfColumns();
		Object[][] data = new Object[TotalNumberOfRows-1][TotalNumberOfColumns];
	//	System.out.println(Excel_Object.getCellData(1,2));
		
		for( int row=1; row<=TotalNumberOfRows-1; row++) 
		{
			for( int column=0; column<=TotalNumberOfColumns-1; column++) 
			{
				data[row-1][column] = Excel_Object.getCellData(row,column);	
			}
		}
		System.out.println("data is " + data);
		return data;
	}
	
	@AfterTest
	public void Cleanup() 
	{
		driver.quit();
		log.info("Test case execution completed");
	}
}

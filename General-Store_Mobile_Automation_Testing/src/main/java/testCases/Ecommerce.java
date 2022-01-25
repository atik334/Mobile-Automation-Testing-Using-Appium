package testCases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.Base_Setup;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.Checkout;
import pageObjects.HomePage;
import pageObjects.Store_Products;
import utilities.Utilities;

@Test
public class Ecommerce extends Base_Setup {
	
	public void mySecondTest() throws MalformedURLException, InterruptedException {
		
		AndroidDriver<AndroidElement> driver = capabilities();
		Thread.sleep(2000);
		
		HomePage hpage=new HomePage(driver);
		
		hpage.nameField.sendKeys("Atika");
		Thread.sleep(2000);
		
		hpage.femaleButton.click();
		Thread.sleep(2000);
		
		hpage.countries.click();
		Thread.sleep(2000);
		
		Utilities util = new Utilities(driver);
		util.scrollToText("Bangladesh");
		Thread.sleep(2000);
		
		hpage.chooseBangladesh.click();
	    Thread.sleep(2000);
		
	    hpage.letsShopBtn.click();
	    Thread.sleep(2000);
		
		Store_Products prodc = new Store_Products(driver);
		prodc.addToCart.get(1).click();
		Thread.sleep(2000);
		prodc.addToCart.get(0).click();
		Thread.sleep(2000);
		prodc.cart.click();
		
       Checkout cPage= new Checkout(driver);
		
		double sum = 0;
		int count = cPage.productList.size();

		for (int i = 0; i < count; i++) {
			String amountWithDollarSign = cPage.productList.get(i).getText();
			double onlyDoubleAmount = getAmount(amountWithDollarSign);
			sum = sum + onlyDoubleAmount;
		}
		System.out.println("SumOfProducts individually: " + sum);

		String total = cPage.total.getText();
		double totalValue = getAmount(total);
		System.out.println("TotalValue from the application: " + totalValue);

		Assert.assertEquals(sum, totalValue);
		
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		driver.quit();
		

	}
	
	
	public static double getAmount(String amountWithDollarSign) {
		String amountWithOutDollarSign = amountWithDollarSign.substring(1);
		double amountOnlyDouble = Double.parseDouble(amountWithOutDollarSign);
		return amountOnlyDouble;
	}

	 
	}


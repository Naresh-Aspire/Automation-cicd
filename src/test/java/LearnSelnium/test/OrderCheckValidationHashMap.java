package LearnSelnium.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import LearnSelnium.TestComponenets.BaseTest;
import LearnSelnium.pageobjectmodule.CartPage;
import LearnSelnium.pageobjectmodule.CatalogPage;
import LearnSelnium.pageobjectmodule.CheckoutPage;
import LearnSelnium.pageobjectmodule.ConfirmationPage;
import LearnSelnium.pageobjectmodule.LandingPage;
import LearnSelnium.pageobjectmodule.OrderHistoryPage;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderCheckValidationHashMap extends BaseTest{
 @Test(dataProvider="getdataHashMap")
	public void submitOrderHashMap(HashMap<String,String> input) throws IOException, InterruptedException {
		String productvalue =input.get("Product");
	    CatalogPage catalogpage = landingpage.loginApplication(input.get("email"),input.get("Password"));
        List<WebElement> productname = catalogpage.getProductList();
        catalogpage.addProductToCart(productvalue);
		CartPage cartpage = catalogpage.goToCartPage();
        cartpage.getProductListinCartPage(productvalue);
		Boolean matchvalue = cartpage.getProductListinCartPage(productvalue);
		Assert.assertTrue(matchvalue);
		CheckoutPage checkoutpage = cartpage.goToCheckoutPage();
        checkoutpage.selectCountryIndia();
		ConfirmationPage confirmationpage = checkoutpage.goToOrderSummary();
		String confirmmessage = confirmationpage.getconfirmationmessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		}


	@DataProvider
	public Object[][] getdataHashMap() {
        HashMap<String, String> obj = new HashMap<String, String>();
	    obj.put("email", "testingnaresh@gmail.com");
	    obj.put("Password", "Aspiremay@12345");
	    obj.put("Product", "ZARA COAT 3");
        HashMap<String, String> obj1 = new HashMap<String, String>();
	    obj1.put("email", "testingnaresh1@gmail.com");
	    obj1.put("Password", "Aspiremay@12345");
	    obj1.put("Product", "ADIDAS ORIGINAL");

	    return new Object[][] { { obj }, { obj1 } };
	}

	
	@Test(dataProvider="getdataHashMap")
	public void cartPageProductCheckHashMap(HashMap<String,String> input) throws IOException, InterruptedException {
		String productvalue =input.get("Product");
	
		CatalogPage catalogpage = landingpage.loginApplication(input.get("email"),input.get("Password"));
        List<WebElement> productname = catalogpage.getProductList();

		catalogpage.addProductToCart(productvalue);
		CartPage cartpage = catalogpage.goToCartPage();

		cartpage.getProductListinCartPage(productvalue);
		Boolean matchvalue = cartpage.getProductListinCartPage(productvalue);
		Assert.assertTrue(matchvalue);

		
	}
	
	@Test(dependsOnMethods={"submitOrderHashMap"},dataProvider="getdataHashMap")
	public void orderHistoryValidationHashMap(HashMap<String,String> input) {
		
		String productvalue =input.get("Product");
		CatalogPage catalogpage = landingpage.loginApplication(input.get("email"),input.get("Password"));
		OrderHistoryPage orderhistory = catalogpage.goToOrdersPage();
		Assert.assertTrue(orderhistory.VerifyOrderHistory(productvalue));
		
	}
	
	

}

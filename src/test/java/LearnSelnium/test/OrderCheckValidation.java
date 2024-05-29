package LearnSelnium.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderCheckValidation extends BaseTest{

	
	@Test(groups =("Smoke"))	
	public void submitOrder() throws IOException, InterruptedException {
		String productvalue = "ZARA COAT 3";
	
		CatalogPage catalogpage = landingpage.loginApplication("testingnaresh@gmail.com", "Aspiremay@12345");
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


	
	@Test
	public void cartPageProductCheck() throws IOException, InterruptedException {
		String productvalue = "ADIDAS ORIGINAL";
	
		CatalogPage catalogpage = landingpage.loginApplication("testingnaresh@gmail.com", "Aspiremay@12345");
        List<WebElement> productname = catalogpage.getProductList();

		catalogpage.addProductToCart(productvalue);
	    CartPage cartpage = catalogpage.goToCartPage();

		cartpage.getProductListinCartPage(productvalue);
		Boolean matchvalue = cartpage.getProductListinCartPage(productvalue);
		Assert.assertTrue(matchvalue);

		
	}
	
	@Test(dependsOnMethods={"submitOrder"})
	public void orderHistoryValidation() {
		
		String productvalue = "ZARA COAT 3";
		
		CatalogPage catalogpage = landingpage.loginApplication("testingnaresh@gmail.com", "Aspiremay@12345");
		OrderHistoryPage orderhistory = catalogpage.goToOrdersPage();
		Assert.assertTrue(orderhistory.VerifyOrderHistory(productvalue));
		
	}
}

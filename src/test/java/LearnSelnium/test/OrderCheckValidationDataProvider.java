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
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderCheckValidationDataProvider extends BaseTest{

	
	@Test(dataProvider="getdata",groups =("Smoke1"))	
	public void submitOrderDataProvider(String email,String Password, String Product) throws IOException, InterruptedException {
		String productvalue = Product;
	
		CatalogPage catalogpage = landingpage.loginApplication(email, Password);
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
	public Object[][] getdata()
	{
		//Object[][] data={{"testingnaresh@gmail.com", "Aspiremay@12345","ZARA COAT 3"},
		//{"testingnaresh@gmail.com", "Aspiremay@12345","ADIDAS ORIGINAL"}};
		//return data;
		return new Object[][] {{"testingnaresh@gmail.com", "Aspiremay@12345","ZARA COAT 3"},
			{"testingnaresh1@gmail.com", "Aspiremay@12345","ADIDAS ORIGINAL"}};
	}

	
	@Test(dataProvider="getdata")
	public void cartPageProductCheckDataProvider(String email,String Password, String Product) throws IOException, InterruptedException {
		String productvalue = Product;
	
		CatalogPage catalogpage = landingpage.loginApplication(email,Password);
        List<WebElement> productname = catalogpage.getProductList();

		catalogpage.addProductToCart(productvalue);
		CartPage cartpage = catalogpage.goToCartPage();

		cartpage.getProductListinCartPage(productvalue);
		Boolean matchvalue = cartpage.getProductListinCartPage(productvalue);
		Assert.assertTrue(matchvalue);

		
	}
	
	@Test(dataProvider="getdata",dependsOnMethods={"submitOrderDataProvider"})
	public void orderHistoryValidationDataProvider(String email,String Password, String Product) {
		
		String productvalue = "ZARA COAT 3";
		
		CatalogPage catalogpage = landingpage.loginApplication(email,Password);
		OrderHistoryPage orderhistory = catalogpage.goToOrdersPage();
		Assert.assertTrue(orderhistory.VerifyOrderHistory(productvalue));
		
	}
	
	
}

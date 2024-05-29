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

public class OrderCheckValidationJSONData extends BaseTest{
 @Test(dataProvider="getdataJSONData")
	public void submitOrderJSONData(HashMap<String,String> input) throws IOException, InterruptedException {
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
	public Object[][] getdataJSONData() throws IOException {
     
       List <HashMap<String,String>> data=getJsonDataToHashMap(System.getProperty("user.dir") + "/src/test/java/LearnSelnium/data/data.json");
	    return new Object[][] { {data.get(0)}, {data.get(1) } };
	    //here is way to retrieve the hashmap from the list
	}

	
	@Test(dataProvider="getdataJSONData")
	public void cartPageProductCheckJSONData(HashMap<String,String> input) throws IOException, InterruptedException {
		String productvalue =input.get("Product");
	
		CatalogPage catalogpage = landingpage.loginApplication(input.get("email"),input.get("Password"));
        List<WebElement> productname = catalogpage.getProductList();

		catalogpage.addProductToCart(productvalue);
		CartPage cartpage = catalogpage.goToCartPage();

		cartpage.getProductListinCartPage(productvalue);
		Boolean matchvalue = cartpage.getProductListinCartPage(productvalue);
		Assert.assertTrue(matchvalue);

		
	}
	
	@Test(dependsOnMethods={"submitOrderJSONData"},dataProvider="getdataJSONData")
	public void orderHistoryValidationJSONData(HashMap<String,String> input) {
		
		String productvalue =input.get("Product");
		CatalogPage catalogpage = landingpage.loginApplication(input.get("email"),input.get("Password"));
		OrderHistoryPage orderhistory = catalogpage.goToOrdersPage();
		Assert.assertTrue(orderhistory.VerifyOrderHistory(productvalue));
		
	}
	
	

}

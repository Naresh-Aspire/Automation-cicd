package LearnSelnium.stepdefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import LearnSelnium.TestComponenets.BaseTest;
import LearnSelnium.pageobjectmodule.CartPage;
import LearnSelnium.pageobjectmodule.CatalogPage;
import LearnSelnium.pageobjectmodule.CheckoutPage;
import LearnSelnium.pageobjectmodule.ConfirmationPage;
import LearnSelnium.pageobjectmodule.LandingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepdefinationImplemnation extends BaseTest {
	public LandingPage landingpage;
	public CatalogPage catalogpage;
	public ConfirmationPage confirmationpage;
	public CartPage cartpage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingpage = launchApplication();

	}

	@Given("^Logged in the username(.+) and password (.+)$")
	public void Logged_in_the_username_and_password(String username, String password) {
		catalogpage = landingpage.loginApplication(username, password);
	}

	@When("^I add product name (.+) from the cart.$")
	public void I_add_product_name_from_the_cart(String product) throws InterruptedException {
		List<WebElement> productname = catalogpage.getProductList();

		catalogpage.addProductToCart(product);
	}

	@When("Checkout {string} and submit the order")
    public void checkout_and_submit_the_order(String product) {
        cartpage = catalogpage.goToCartPage();
		
        Boolean matchvalue = cartpage.getProductListinCartPage(product);
        Assert.assertTrue(matchvalue);

        CheckoutPage checkoutpage = cartpage.goToCheckoutPage();
        checkoutpage.selectCountryIndia();
        ConfirmationPage confirmationpage = checkoutpage.goToOrderSummary();

        
    }

	@Then("{string} message is displayed on confirmation page.")
	public void message_is_displayed_on_confirmation_page(String string) {
		String confirmmessage = confirmationpage.getconfirmationmessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase(string));
		driver.close();

	}
	
	@Then("{string} error message is displayed.")
	public void error_message_is_displayed(String string)
	{
		 Assert.assertEquals(landingpage.loginError(),string); 
		 driver.close();
		
	}

}

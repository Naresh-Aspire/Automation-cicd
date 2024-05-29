package LearnSelnium.pageobjectmodule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LearnSelenium.AbstractComponents.AbstractComponenets;

public class CartPage extends AbstractComponenets  {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);
}

	@FindBy(xpath = "//div[@class='cartSection']/h3 ")
	List<WebElement> productslists;
	
	@FindBy(xpath = "//li[@class='totalRow']/button")
	WebElement checkoutPage;
	
public Boolean getProductListinCartPage(String productvalue) {
	Boolean matchvalue = productslists.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase("ZARA COAT 3"));
	return matchvalue;
	 
 }

public CheckoutPage goToCheckoutPage()
{
	checkoutPage.click();	
	
	CheckoutPage checkoutpage=new CheckoutPage(driver);
	return checkoutpage;
}

}
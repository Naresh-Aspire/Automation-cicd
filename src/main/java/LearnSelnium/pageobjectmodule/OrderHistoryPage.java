package LearnSelnium.pageobjectmodule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import LearnSelenium.AbstractComponents.AbstractComponenets;

public class OrderHistoryPage extends AbstractComponenets  {

	WebDriver driver;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);
}

	@FindBy(xpath ="//tbody/tr//td[2]")
	List<WebElement> orderedProductList;
	
	
	public Boolean VerifyOrderHistory(String productvalue)
	{
	Boolean matchvalue =orderedProductList.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase("ZARA COAT 3"));
	return matchvalue;
		 
	 }





}
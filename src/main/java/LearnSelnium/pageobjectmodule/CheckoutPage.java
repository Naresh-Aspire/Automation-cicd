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

public class CheckoutPage extends AbstractComponenets  {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);
}

	@FindBy(xpath ="//input[@placeholder='Select Country']")
	WebElement enterIND;
	
	By countrydropdown =By.cssSelector(".ta-results");
	
	@FindBy(xpath ="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectINDIA;
	
	@FindBy(css =".btnn.action__submit.ng-star-inserted")
	WebElement ordersummary;

	
public void selectCountryIndia() {
	enterIND.sendKeys("ind");
	waitforElementtoAppear(countrydropdown);
	selectINDIA.sendKeys(Keys.ENTER);
}

public ConfirmationPage goToOrderSummary()
{
	ordersummary.click();
	
	ConfirmationPage confirmationpage=new ConfirmationPage(driver);
	return confirmationpage;
}

}
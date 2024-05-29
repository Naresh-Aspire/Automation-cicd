package LearnSelnium.pageobjectmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LearnSelenium.AbstractComponents.AbstractComponenets;

public class ConfirmationPage extends AbstractComponenets{
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css =".hero-primary")
	WebElement confirmationmesage;
	//String confirmmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	//Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	public String getconfirmationmessage()
	{
		return confirmationmesage.getText();
		
	}

}

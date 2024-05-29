package LearnSelnium.pageobjectmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LearnSelenium.AbstractComponents.AbstractComponenets;

public class LandingPage extends AbstractComponenets  {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);
}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement loginError;
	
	@FindBy(xpath = "//input[@formcontrolname='userEmail']")
	WebElement recoveryEmail;
	
	@FindBy(id = "userPassword")
	WebElement recoveryPassword;
	
	@FindBy(id = "confirmPassword")
	WebElement ConfirmPassword;
	
	@FindBy(css = ".forgot-password-link")
	WebElement forgotPasswordlink;
	
	@FindBy(css = ".card-title.text-center")
	WebElement forgotPasswordTitle;
	
	@FindBy(css = ".btn.btn-custom.btn-block")
	WebElement saveNewPassword;
	
	//@FindBy(css = ".ng-tns-c4-4.toast-title.ng-star-inserted")
	@FindBy(css = ".toast-success")
	WebElement successMessage;
	
	
	
	
	public CatalogPage loginApplication(String email,String Password) {
	userEmail.sendKeys(email);
	userPassword.sendKeys(Password);
	submit.click();
	CatalogPage catalogpage=new CatalogPage(driver);
	return catalogpage;

	 
 }

public void goTo()
{
	driver.get("https://rahulshettyacademy.com/client");
	//https://rahulshettyacademy.com/client/auth/login
	
//	ChromeOptions option =new  ChromeOptions();
//	option.setAcceptInsecureCerts(true);
//	System.setProperty("webdriver.chrome.driver","/D:/Automation/chromedriver-win64/chromedriver.exe");
//	ChromeDriver driver =new ChromeDriver(option);
	
}



public String loginError() {
	//waitforWebElementtoAppear(loginError);
	return loginError.getText();
	
	
}

public String forgotPasswordLink() {
	
	forgotPasswordlink.click();
	return forgotPasswordTitle.getText();
	
	
}

public void recoveryPassword(String email,String password) {
	recoveryEmail.sendKeys(email);
	recoveryPassword.sendKeys(password);
	ConfirmPassword.sendKeys(password);
	saveNewPassword.click();
    }

public String recoveryPasswordSuccessful()
{
	waitforWebElementtoAppear(successMessage);
	return successMessage.getText();
}


}
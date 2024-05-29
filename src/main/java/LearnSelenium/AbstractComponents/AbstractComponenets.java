package LearnSelenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LearnSelnium.pageobjectmodule.CartPage;
import LearnSelnium.pageobjectmodule.OrderHistoryPage;

public class AbstractComponenets {

	WebDriver driver;

	public AbstractComponenets(WebDriver driver) {// scope of the driver inside the method
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orders;
	

	public void waitforElementtoAppear(By Findby) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
	}
	
	public void waitforWebElementtoAppear(WebElement Findby) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Findby));
	}

	public void waitforElementtoDisappear(WebElement Elem) throws InterruptedException {
		Thread.sleep(1000);

//		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(Elem));
	}

	public CartPage goToCartPage() {
		cart.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderHistoryPage goToOrdersPage() {
		orders.click();
		OrderHistoryPage orderhistory = new OrderHistoryPage(driver);
		return orderhistory;
	}

}

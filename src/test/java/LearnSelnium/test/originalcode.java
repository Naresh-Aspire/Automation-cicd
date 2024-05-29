package LearnSelnium.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import LearnSelnium.pageobjectmodule.LandingPage;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class originalcode {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String productvalue = "ZARA COAT 3";

		driver.get("https://rahulshettyacademy.com/client");

		
		driver.findElement(By.id("userEmail")).sendKeys("testingnaresh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Aspiremay@12345");
		driver.findElement(By.id("login")).click();

		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4.col-md-6.col-sm-10 ")));
		List<WebElement> productname = driver.findElements(By.cssSelector(".col-lg-4.col-md-6.col-sm-10 "));

		WebElement prod = productname.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		prod.findElement(By.xpath("//button[2]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartproduct = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

		Boolean matchvalue = cartproduct.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase("ZARA COAT 3"));

		Assert.assertTrue(matchvalue);
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();

		// payment payment page
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();

		String confirmmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();
	}

}

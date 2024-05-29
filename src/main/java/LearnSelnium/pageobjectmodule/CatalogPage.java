package LearnSelnium.pageobjectmodule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LearnSelenium.AbstractComponents.AbstractComponenets;

public class CatalogPage extends AbstractComponenets {

	WebDriver driver;

	public CatalogPage(WebDriver driver) {
		super(driver);// asking parent class to invoke constructors
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".col-lg-4.col-md-6.col-sm-10 ")
	List<WebElement> products;

	By productby = By.cssSelector(".col-lg-4.col-md-6.col-sm-10");
	
	By addingtocart=By.xpath("//button[2]");
	
	By toastmessage=By.cssSelector(".toast-container");
	
	@FindBy(css = ".ng-animating")
	WebElement nganimating;

	public List<WebElement> getProductList() {
		waitforElementtoAppear(productby);
		return products;
	}

	public WebElement getProductByName(String productvalue) {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productvalue)).findFirst()
				.orElse(null);

		return prod;

	}
	
	public void addProductToCart(String productvalue) throws InterruptedException
	{
		WebElement prod=getProductByName(productvalue);	
		prod.findElement(addingtocart).click();
		waitforElementtoAppear(toastmessage);
		waitforElementtoDisappear(nganimating);
		
		
		
	}

}
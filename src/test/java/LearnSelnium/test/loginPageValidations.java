package LearnSelnium.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import LearnSelnium.TestComponenets.BaseTest;
import LearnSelnium.TestComponenets.Itryanalyzer;
import LearnSelnium.pageobjectmodule.CartPage;
import LearnSelnium.pageobjectmodule.CatalogPage;
import LearnSelnium.pageobjectmodule.CheckoutPage;
import LearnSelnium.pageobjectmodule.ConfirmationPage;

public class loginPageValidations extends BaseTest{

	
	@Test(retryAnalyzer=Itryanalyzer.class)	
	public void loginErrorMessage() throws IOException, InterruptedException {
	
	  landingpage.loginApplication("testingnaresherror@gmail.com", "Aspiremay@12345");
	  Assert.assertEquals(landingpage.loginError(),"Incorrect email or password."); 
      System.out.println(landingpage.loginError());
		
	}
	
	@Test
	public void getForgotPasswordLinkText()
	{
		  Assert.assertEquals(landingpage.forgotPasswordLink(),"Enter New Password"); 
	}
	
	@Test(groups =("Smoke"))
	public void createNewPassword() {
		landingpage.forgotPasswordLink();
		landingpage.recoveryPassword("testingnaresh@gmail.com", "Aspiremay@12345");
	    Assert.assertEquals(landingpage.recoveryPasswordSuccessful(),"Password Changed Successfully");
	    System.out.println(landingpage.recoveryPasswordSuccessful());
		
	}


}

package LearnSelnium.TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import LearnSelnium.pageobjectmodule.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializedriver() throws IOException {

		Properties prop = new Properties();
		// How to send a file as a input stream?
		// FileInputStream obj = new
		// FileInputStream("/C/Users/naresh.murugan/eclipse-workspace/Seleniume2e/src/main/java/LearnSelenium/resources/Global.properties");

		FileInputStream obj = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/LearnSelenium/resources/Global.properties");
		prop.load(obj);// the file has to sent has a input stream

		String Browsername = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// String Browsername = prop.getProperty("browser");// setting the global
		// properties
		if (Browsername.contains("chrome")) {
			ChromeOptions Options = new ChromeOptions();
			
			WebDriverManager.chromedriver().setup();
			if (Browsername.contains("headless")) {
				Options.addArguments("headless");
			}

			driver = new ChromeDriver(Options);
			driver.manage().window().setSize(new Dimension(1440,900));//asking the code to run in the full size screen


		} else if (Browsername.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/D:/Automation/Firefox/geckodriver.exe");
			String firefoxBinaryPath = "C:/Users/naresh.murugan/AppData/Local/Mozilla Firefox/firefox.exe";
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary(firefoxBinaryPath);
			driver = new FirefoxDriver(options);
		} else if (Browsername.equals("edge")) {
			// Set the path to the EdgeDriver executable
			System.setProperty("webdriver.edge.driver", "/path/to/edgedriver.exe");
			// Create a new instance of the EdgeDriver
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToHashMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		ObjectMapper obj = new ObjectMapper();

		List<HashMap<String, String>> data = obj.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);// Taking screenshot
		File file = new File((System.getProperty("user.dir") + "//reports//" + testcaseName + ".png")); // screenshot
																										// stored space
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializedriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;// why we are returning the landingpage->We need landing page in order to proceed to further tests
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		// driver.close();
		if (driver != null) {
			driver.quit();
		}
	}

}

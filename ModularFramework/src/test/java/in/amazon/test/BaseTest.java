package in.amazon.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import commonLibs.implementation.CommonDriver;
import in.amazon.pages.Homepage;

public class BaseTest {

	CommonDriver cmnDriver;

	String url = "https://amazon.in";

	Homepage homepage;

	WebDriver driver;

	@BeforeClass
	public void setup() throws Exception {

		invokeBrowser();

		pageInitialization();

	}

	@AfterClass
	public void cleanUp() throws Exception {

		closeBrowser();

	}

	private void closeBrowser() throws Exception {
		cmnDriver.closeAllBrowsers();
	}

	private void invokeBrowser() throws Exception {
		cmnDriver = new CommonDriver("chrome");

		cmnDriver.setPageloadTimeout(90);

		cmnDriver.setElementDetectionTimeout(20);

		cmnDriver.navigateToUrl(url);

		driver = cmnDriver.getDriver();

	}

	private void pageInitialization() {
		homepage = new Homepage(driver);
	}

}

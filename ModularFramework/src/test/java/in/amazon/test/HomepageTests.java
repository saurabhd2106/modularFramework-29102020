package in.amazon.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;
import in.amazon.pages.Homepage;

public class HomepageTests {

	CommonDriver cmnDriver;

	String url = "https://amazon.in";

	Homepage homepage;

	WebDriver driver;

	@BeforeClass
	public void setup() throws Exception {
		cmnDriver = new CommonDriver("chrome");

		cmnDriver.setPageloadTimeout(90);

		cmnDriver.setElementDetectionTimeout(20);

		cmnDriver.navigateToUrl(url);

		driver = cmnDriver.getDriver();

		homepage = new Homepage(driver);
	}

	@AfterClass
	public void cleanUp() throws Exception {
		cmnDriver.closeAllBrowsers();

	}

	@Test
	public void verifySearchProduct() throws Exception {
		String product = "Apple Watch";

		String category = "Electronics";

		String result = homepage.searchProduct(product, category);
		
		System.out.println(result);

	}

}

package in.amazon.demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;
import in.amazon.designPattern.AmazonPomHomepage;

public class DemoAmazonPomHomepage {

	CommonDriver cmnDriver;
	
	AmazonPomHomepage homepage;
	
	private WebDriver driver;

	@BeforeMethod
	public void verifyInvokeBrowser() throws Exception{
		
		cmnDriver = new CommonDriver("chrome");
		
		cmnDriver.setElementDetectionTimeout(20);
		
		cmnDriver.setPageloadTimeout(90);
		
		cmnDriver.navigateToUrl("https://amazon.in");
		
		driver = cmnDriver.getDriver();
		
		homepage = new AmazonPomHomepage(driver);
		
	}

	@Test
	public void verifySearchOperation() throws Exception {
		
		homepage.searchProduct("Apple Watch", "Electronics");

	}
	
	@AfterMethod
	public void closeBrowser() throws Exception{
		cmnDriver.closeAllBrowsers();
	}

}

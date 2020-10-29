package in.amazon.tests;

import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;

public class DemoCommonDriver {
	
	CommonDriver cmnDriver;
	
	@Test
	public void verifyInvokeBrowser() throws Exception{
		
		cmnDriver = new CommonDriver("chrome");
		
		cmnDriver.setElementDetectionTimeout(20);
		
		cmnDriver.setPageloadTimeout(90);
		
		cmnDriver.navigateToUrl("https://test.qatechhub.com");
		
		cmnDriver.closeAllBrowsers();
		
	}

}

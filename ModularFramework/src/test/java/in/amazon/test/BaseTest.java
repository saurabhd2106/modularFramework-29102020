package in.amazon.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import commonLibs.implementation.CommonDriver;
import commonLibs.utils.DateUtils;
import commonLibs.utils.ExtentReportUtils;
import in.amazon.pages.Homepage;

public class BaseTest {

	CommonDriver cmnDriver;

	String url = "https://amazon.in";

	Homepage homepage;

	WebDriver driver;

	ExtentReportUtils extentReportUtils;

	static String htmlReportFilename;
	static String currentWorkingDirectory;
	static String executeStartTime;

	static {
		executeStartTime = DateUtils.getCurrentDate();

		currentWorkingDirectory = System.getProperty("user.dir");

		htmlReportFilename = String.format("%s/reports/amazon-report-%s.html", currentWorkingDirectory,
				executeStartTime);
		
		System.out.println(htmlReportFilename);

	}

	@BeforeSuite
	public void preSetup() {
		initializeReports();
	}

	@AfterSuite
	public void postCLeanup() {

		flushReports();

	}

	@BeforeClass
	public void setup() throws Exception {

		extentReportUtils.createATestcase("Setup - All setup for test execution");

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
		String browserType = "chrome";

		cmnDriver = new CommonDriver(browserType);

		extentReportUtils.addLogStatus(Status.INFO, "Browser invoked is - " + browserType);

		int pageloatimeout = 90;
		cmnDriver.setPageloadTimeout(pageloatimeout);
		
		extentReportUtils.addLogStatus(Status.INFO, "Pageload timeout - " + pageloatimeout);

		int elementDetectionTimeout = 30;
		cmnDriver.setElementDetectionTimeout(elementDetectionTimeout);

		extentReportUtils.addLogStatus(Status.INFO, "Implict timeout - " + elementDetectionTimeout);
		
		cmnDriver.navigateToUrl(url);

		driver = cmnDriver.getDriver();

	}

	private void pageInitialization() {
		homepage = new Homepage(driver);
	}

	private void initializeReports() {

		extentReportUtils = new ExtentReportUtils(htmlReportFilename);
	}

	private void flushReports() {

		extentReportUtils.flushReport();

	}

}

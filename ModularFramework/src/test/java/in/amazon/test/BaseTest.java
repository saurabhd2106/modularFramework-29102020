package in.amazon.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.ScreenshotControl;
import commonLibs.utils.ConfigPropertyUtils;
import commonLibs.utils.DateUtils;
import commonLibs.utils.ExtentReportUtils;
import in.amazon.pages.Homepage;

public class BaseTest {

	CommonDriver cmnDriver;

	String url;

	Homepage homepage;

	WebDriver driver;

	ExtentReportUtils extentReportUtils;

	static String htmlReportFilename;
	static String currentWorkingDirectory;
	static String executeStartTime;
	static String configFilename;
	static Properties configPropeties;

	ScreenshotControl screenshotControl;

	static {
		executeStartTime = DateUtils.getCurrentDate();

		currentWorkingDirectory = System.getProperty("user.dir");

		htmlReportFilename = String.format("%s/reports/amazon-report-%s.html", currentWorkingDirectory,
				executeStartTime);

		System.out.println(htmlReportFilename);

	}

	@BeforeSuite
	public void preSetup() throws Exception {
		configFilename = String.format("%s/config/config.properties", currentWorkingDirectory);
		configPropeties = ConfigPropertyUtils.configFileReader(configFilename);

		initializeReports();
	}

	@AfterSuite
	public void postCLeanup() {

		flushReports();

	}

	@BeforeMethod
	public void setup() {

		try {
			extentReportUtils.createATestcase("Setup - All setup for test execution");

			invokeBrowser();

			classInitializations();

			pageInitialization();
		} catch (Exception e) {

			extentReportUtils.addLogStatus(Status.FAIL, "Setup failed..");
			extentReportUtils.addLogStatus(Status.ERROR, e.getMessage());
		}
	}

	@AfterMethod
	public void postTestSteps(ITestResult testResult) throws Exception {

		if (testResult.getStatus() == ITestResult.SUCCESS) {
			extentReportUtils.addLogStatus(Status.PASS, "All steps of test passed successfully");
		} else if (testResult.getStatus() == ITestResult.FAILURE) {

			String testcaseName = testResult.getName();

			String screenshotCaptureTime = DateUtils.getCurrentDate();

			String screenshotFilename = String.format("%s/screenshot/%s-%s.jpeg", currentWorkingDirectory, testcaseName,
					screenshotCaptureTime);

			screenshotControl.captureAndSaveScreenshot(screenshotFilename);

			extentReportUtils.addLogStatus(Status.FAIL, "One or more steps failed");

			extentReportUtils.addScreenshotToTheLogs(screenshotFilename);

		} else {
			extentReportUtils.addLogStatus(Status.SKIP, "Test case skipped...");
		}

		closeBrowser();

	}

	private void closeBrowser() throws Exception {
		cmnDriver.closeAllBrowsers();
	}

	private void invokeBrowser() throws Exception {
		String browserType = configPropeties.getProperty("browserType");

		String url = configPropeties.getProperty("baseUrl");
		
		String env = configPropeties.getProperty("env");

		cmnDriver = new CommonDriver(browserType, env);

		extentReportUtils.addLogStatus(Status.INFO, "Browser invoked is - " + browserType);

		int pageloatimeout = Integer.parseInt(configPropeties.getProperty("pageloadTimeout"));
		cmnDriver.setPageloadTimeout(pageloatimeout);

		extentReportUtils.addLogStatus(Status.INFO, "Pageload timeout - " + pageloatimeout);

		int elementDetectionTimeout = Integer.parseInt(configPropeties.getProperty("elementDetectionTimeout"));
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

	private void classInitializations() {
		screenshotControl = new ScreenshotControl(driver);

	}

	private void flushReports() {

		extentReportUtils.flushReport();

	}

}

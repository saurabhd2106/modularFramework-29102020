package in.amazon.test;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import org.testng.annotations.Test;

public class HomepageTests extends BaseTest {

	@Test
	public void verifySearchProduct() throws Exception {
		extentReportUtils.createATestcase("TC001 - Verify Search Product");

		String product = "Apple Watch";

		String category = "Electronics";

		extentReportUtils.addLogStatus(Status.INFO, "Product searched - " + product);

		extentReportUtils.addLogStatus(Status.INFO, "Category selected - " + category);

		String result = homepage.searchProduct(product, category);

		extentReportUtils.addLogStatus(Status.INFO, "Results - " + result);

		System.out.println(result);

	}

}

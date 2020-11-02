package in.amazon.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class HomepageTests extends BaseTest {

	@Test
	public void verifySearchProduct() {
		try {

			extentReportUtils.createATestcase("TC001 - Verify Search Product");

			String product = "Apple Watch";

			String category = "Electronics";

			extentReportUtils.addLogStatus(Status.INFO, "Product searched - " + product);

			extentReportUtils.addLogStatus(Status.INFO, "Category selected - " + category);

			String result = homepage.searchProduct(product, category);

			extentReportUtils.addLogStatus(Status.INFO, "Results - " + result);

			String expectedResult = "1-24 of over 2,000 results for Electronics : \"Apple Watch\"";

			Assert.assertEquals(result, expectedResult);

		} catch (Exception e) {
			
			extentReportUtils.addLogStatus(Status.ERROR, e.getMessage());

		}

	}

}

package in.amazon.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import utils.TestDataSource;
import utils.TestDataSourceFromDatabase;

public class HomepageTests extends BaseTest {

	@Test(dataProvider = "getDataforSearchProduct", dataProviderClass = TestDataSource.class)
	public void verifySearchProduct(String product, String category) throws Exception {
		extentReportUtils.createATestcase("TC001 - Verify Search Product");

		extentReportUtils.addLogStatus(Status.INFO, "Product searched - " + product);

		extentReportUtils.addLogStatus(Status.INFO, "Category selected - " + category);

		String result = homepage.searchProduct(product, category);

		extentReportUtils.addLogStatus(Status.INFO, "Results - " + result);

		
		  String expectedResult =
		  "1-24 of over 2,000 results for Electronics : \"Apple Watch\"";
		  
		  Assert.assertEquals(result, expectedResult);
		 

	}

}

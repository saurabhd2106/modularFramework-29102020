package in.amazon.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class HomepageTests extends BaseTest {

	@Test
	public void verifySearchProduct() throws Exception {
		String product = "Apple Watch";

		String category = "Electronics";

		String result = homepage.searchProduct(product, category);

		System.out.println(result);

	}

}

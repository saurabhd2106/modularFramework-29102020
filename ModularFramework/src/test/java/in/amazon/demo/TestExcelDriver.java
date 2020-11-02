package in.amazon.demo;

import org.testng.annotations.Test;

import commonLibs.utils.ExcelDriver;

public class TestExcelDriver {

	@Test
	public void verifyExcelDriver() throws Exception {
		String filename = System.getProperty("user.dir") + "/testDataInputFile/" + "data.xlsx";
		String sheetname = "Test Data";
		ExcelDriver excelDriver;

		excelDriver = new ExcelDriver();

		excelDriver.createWorkbook(filename);

		excelDriver.openWorkbook(filename);

		excelDriver.createSheet(sheetname);

		excelDriver.setCellData(sheetname, 1, 1, "Saurabh");
		excelDriver.setCellData(sheetname, 1, 2, "Dhingra");
		excelDriver.setCellData(sheetname, 2, 1, "Gaurav");
		excelDriver.setCellData(sheetname, 2, 2, "Yadav");

		excelDriver.saveFile();

		excelDriver.closeFile();

	}

}

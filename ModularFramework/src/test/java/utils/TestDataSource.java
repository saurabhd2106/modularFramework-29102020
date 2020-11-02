package utils;

import org.testng.annotations.DataProvider;

import commonLibs.utils.ExcelDriver;

public class TestDataSource {

	private String currentWorkingDirectory;

	@DataProvider
	public Object[][] getDataforSearchProduct() throws Exception{

		currentWorkingDirectory = System.getProperty("user.dir");

		String excelWorkbook = String.format("%s/testDataInputFile/TestData.xlsx", currentWorkingDirectory);

		String excelSheetname = "SearchProduct";
		
		return readDataFromExcel(excelWorkbook, excelSheetname);

	}

	private Object[][] readDataFromExcel(String workbookFilename, String sheetname) throws Exception {

		ExcelDriver excelDriver = new ExcelDriver();

		excelDriver.openWorkbook(workbookFilename);

		Object[][] data;

		int rowCount, cellCount;

		rowCount = excelDriver.getRowCount(sheetname);

		cellCount = excelDriver.getCellCountFromRow(sheetname, 0);

		data = new Object[rowCount + 1][cellCount];

		for (int row = 0; row <= rowCount; row++) {
			for (int cell = 0; cell < cellCount; cell++) {
				
				data[row][cell] = excelDriver.getCellData(sheetname, row, cell);
				
				
			}
		}
		
		return data;

	}

}

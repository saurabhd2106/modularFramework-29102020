package utils;

import org.testng.annotations.DataProvider;

import commonLibs.utils.DatabaseUtils;

public class TestDataSourceFromDatabase {
	
	@DataProvider
	public Object[][] getDataForSearchProduct() throws Exception{
		
		String server = "localhost";
		int  portnumber = 3306;
		String database = "amazonTestData";
		
		String username = "root";
		String password = "admin@1234";
		
		DatabaseUtils databaseUtils = new DatabaseUtils();
		
		databaseUtils.openConnect(server, database, portnumber, username, password);
		
		String sqlQuery = "select * from searchProductTestData";
		
		Object[][] data = databaseUtils.executeSelectSqlQuery(sqlQuery);
		
		databaseUtils.closeConection();
		
		return data;
		
	}
	
	

}

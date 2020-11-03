package commonLibs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtils {

	private Connection connection;

	public void openConnect(String server, String database, int portNumber, String username, String password)
			throws Exception {
		String connectionString = String.format("jdbc:mysql://%s:%d/%s", server, portNumber, database);

		connection = DriverManager.getConnection(connectionString, username, password);
	}

	public void closeConection() throws Exception {
		connection.close();
	}

	public Object[][] executeSelectSqlQuery(String sqlQuery) throws Exception {

		Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		ResultSet resultSet = stmt.executeQuery(sqlQuery);

		resultSet.last();

		int rowCount = resultSet.getRow();
		int cellCount = resultSet.getMetaData().getColumnCount();

		Object[][] data = new Object[rowCount][cellCount];

		for (int row = 1; row <= rowCount; row++) {
			for (int cell = 1; cell <= cellCount; cell++) {
				data[row - 1][cell - 1] = resultSet.getString(cell);
			}
		}

		return data;

	}

}

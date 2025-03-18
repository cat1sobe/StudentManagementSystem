package Project.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbUtil
{
	private static Connection dbConnection = null;

	public static Connection getConnection()
	{
		if (dbConnection != null)
		{
			return dbConnection;
		}
		else
		{
			try
			{
				// デバッグ用のファイル出力
				java.io.FileWriter fw = new java.io.FileWriter("/tmp/dbutil.log", true);
				fw.write("DbUtil.getConnection() called\n");
				
				InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream("db.properties");
				fw.write("InputStream: " + (inputStream != null ? "not null" : "null") + "\n");

				Properties properties = new Properties();

				if (inputStream != null)
				{
					properties.load(inputStream);

					String dbDriver = properties.getProperty("dbDriver");
					String connectionUrl = properties.getProperty("connectionUrl");
					String userName = properties.getProperty("userName");
					String password = properties.getProperty("password");

					fw.write("dbDriver: " + dbDriver + "\n");
					fw.write("connectionUrl: " + connectionUrl + "\n");
					fw.write("userName: " + userName + "\n");
					fw.write("password: " + (password != null ? "not null" : "null") + "\n");

					Class.forName(dbDriver).newInstance();
					dbConnection = DriverManager.getConnection(connectionUrl, userName, password);
					fw.write("dbConnection: " + (dbConnection != null ? "not null" : "null") + "\n");
				} else {
					fw.write("Failed to load db.properties\n");
				}
				fw.close();
			}
			catch (Exception e)
			{
				try {
					java.io.FileWriter fw = new java.io.FileWriter("/tmp/dbutil.log", true);
					fw.write("Exception in DbUtil.getConnection(): " + e.getMessage() + "\n");
					e.printStackTrace(new java.io.PrintWriter(fw));
					fw.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return dbConnection;
		}
	}
}

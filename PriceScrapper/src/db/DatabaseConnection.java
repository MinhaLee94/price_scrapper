package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
	private final String CONFIG_FILE = "config.properties";
	private final String DEV_ENV = "TEST";
	private final String msjdbc_driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	public Connection conn = null;
	
	public DatabaseConnection() {
		try {
			final String dbAddress = getDBAddress();	
			final String dbId = getId();
			final String dbPwd= getPwd();
			
			Class.forName(msjdbc_driver);
			conn = DriverManager.getConnection(dbAddress, dbId, dbPwd);			
			
			if(conn!=null) 
			{
				System.out.println("Connected to the database.");
			}
			else
			{
				System.out.println("Failed to connect to the database.");
			}
		} catch (ClassNotFoundException e) {
            System.out.println("Failed to load JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        } 
	}
	
	public String getDBAddress() 
	{	
		String dbAddress = "";
		if(DEV_ENV.equalsIgnoreCase("PROD")) {
			dbAddress = "db.address.prod";
		} else if (DEV_ENV.equalsIgnoreCase("TEST")) {
			dbAddress = "db.address.test";
		}
		return getProperty(dbAddress);
	}
	
	public String getId(){
		return getProperty("db.id");
	}
	
	public String getPwd(){
		return getProperty("db.pw");
	}
	
	public String getProperty(String property) {
		Properties prop = new Properties();
		String result = "";
		try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
			prop.load(fis);
            result = prop.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return result;
	}
}

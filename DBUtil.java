package javatraining.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class DBUtil {
	private static String driver,url,user,password;
	
private DBUtil() {
}
static {
	ResourceBundle rsb = ResourceBundle.getBundle("jdbc");
	driver=rsb.getString("jdbc.driver");
    url = rsb.getString("jdbc.url");
    user= rsb.getString("jdbc.user");
    password = rsb.getString("jdbc.password");
	
}
public static Connection getConnection() throws ClassNotFoundException, SQLException {
	Class.forName(driver);
	return DriverManager.getConnection(url,user,password);
}
}

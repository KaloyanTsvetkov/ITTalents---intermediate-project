package controler.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	private static DBConnection conInstance; //instance from the class
    private Connection connection = null; // Connection for MySQL.
    
	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "123");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static DBConnection getInstance() {
		synchronized (DBConnection.class) {
			if(conInstance == null) {
				conInstance = new DBConnection();
			}
		}
		return conInstance;
	}
	
	public Connection getConnection() {
		return connection;
	}

}

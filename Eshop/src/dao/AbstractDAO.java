package dao;

import java.sql.Connection;

import controler.connection.DBConnection;

public abstract class AbstractDAO {
	public static final int INVALID_QUERY = -1;
	private Connection connection;
	
	public AbstractDAO() {
		this.connection = DBConnection.getInstance().getConnection();
	}
	
	public Connection getConnection() {
		return this.connection;
	}

	
}

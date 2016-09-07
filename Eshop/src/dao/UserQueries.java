package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import model.entity.User;

public class UserQueries extends AbstractDAO {
	private static final String INSERT_USER_QUERY = "INSERT INTO users (LOGIN_NAME, LOGIN_PASSWORD, SAULT, IS_ADMIN) VALUES (?, ?, ?, ?)";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String SELECT_USER_BY_NAME = "SELECT * FROM users WHERE LOGIN_NAME = ?";

	private PreparedStatement insertIntoUser = null;
	private PreparedStatement selectUserById = null;
	private PreparedStatement selectUserByName = null;

	//Get User by ID
	public User selectUserById(int id) {
		ResultSet resultSet = null;
		User user = null;

		try {
			selectUserById = getConnection().prepareStatement(SELECT_USER_BY_ID);

			selectUserById.setInt(1, id);

			resultSet = selectUserById.executeQuery();
			while (resultSet.next()) {
				user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getBoolean(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

	//add new User
	public int addNewUser(String loginName, String loginPassword, String sault){
		
		try {
			insertIntoUser = getConnection().prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
			
			insertIntoUser.setString(1, loginName);
			insertIntoUser.setString(2, loginPassword);
			insertIntoUser.setString(3, sault);
			insertIntoUser.setBoolean(4, false);
			
			insertIntoUser.executeUpdate();
			
			ResultSet rs = insertIntoUser.getGeneratedKeys();
			int index = 0;
			if (rs.next()){
				index=rs.getInt(1);
			}

			return index;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return AbstractDAO.INVALID_QUERY;
		
	}
	
	//Get User by Name
	public User selectUserByName(String name) {
		ResultSet resultSet = null;
		User user = null;

		try {
			selectUserByName = getConnection().prepareStatement(SELECT_USER_BY_NAME);

			selectUserByName.setString(1, name);

			resultSet = selectUserByName.executeQuery();
			while (resultSet.next()) {
				user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getBoolean(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class CustomerQueries extends AbstractDAO {
	private static final String INSERT_CUSTOMER_QUERY = "INSERT INTO customers (USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ADRESS_ID) VALUES (?, ?, ?, ?, ?)";

	private PreparedStatement insertIntoCustomer = null;

	// add new Customer transaction
	public int addNewCustomer(String loginName, String loginPassword, String sault, String firstName, String lastName,
			String eMail, int cityId, String street, int postalCode) throws SQLException {
		Connection connection = getConnection();
		try {

			connection.setAutoCommit(false);
			insertIntoCustomer = getConnection().prepareStatement(INSERT_CUSTOMER_QUERY,
					Statement.RETURN_GENERATED_KEYS);

			UserQueries userQ = new UserQueries();
			int indexUser = userQ.addNewUser(loginName, loginPassword, sault);

			if (indexUser <= 0) {
				getConnection().rollback();
			}

			AdressQueries adressQ = new AdressQueries();
			int adressID = adressQ.addNewAdress(cityId, street, postalCode);

			if (adressID <= 0) {
				getConnection().rollback();
			}

			insertIntoCustomer.setInt(1, indexUser);
			insertIntoCustomer.setString(2, firstName);
			insertIntoCustomer.setString(3, lastName);
			insertIntoCustomer.setString(4, eMail);
			insertIntoCustomer.setInt(5, adressID);

			insertIntoCustomer.executeUpdate();

			connection.commit();

			//towa ne raboti zaradi One to One connection...
//			ResultSet rs = insertIntoCustomer.getGeneratedKeys();
//			int index = 0;
//			if (rs.next()) {
//				index = rs.getInt(1);
//			}

			return indexUser;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			getConnection().rollback();
		} 
		
		return AbstractDAO.INVALID_QUERY;
	}

}

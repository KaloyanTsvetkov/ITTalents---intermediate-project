package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import model.entity.Adress;
import model.entity.City;

public class AdressQueries extends AbstractDAO {
	private static final String INSERT_ADRESS_QUERY = "INSERT INTO adresses (CITY_ID, STREET, POSTAL_CODE) VALUES (?, ?, ?)";
	private static final String SELECT_ADRESS_BY_ID = "SELECT * FROM adresses WHERE id = ?";

	private PreparedStatement insertIntoAdress = null;
	private PreparedStatement selectAdressById = null;

	// Get User by ID
	public Adress selectAdressById(int id) {
		ResultSet resultSet = null;
		Adress adress = null;

		try {
			selectAdressById = getConnection().prepareStatement(SELECT_ADRESS_BY_ID);

			selectAdressById.setInt(1, id);

			resultSet = selectAdressById.executeQuery();
			while (resultSet.next()) {
				int cityIndex = resultSet.getInt(2);
				CityQueries cityQ = new CityQueries();
				City city = cityQ.selectUserById(cityIndex);
				adress = new Adress(resultSet.getInt(1), resultSet.getString(3), resultSet.getInt(4), city);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adress;

	}

	// add new User
	public int addNewAdress(int cityId, String street, int postalCode) {

		try {
			insertIntoAdress = getConnection().prepareStatement(INSERT_ADRESS_QUERY, Statement.RETURN_GENERATED_KEYS);

			insertIntoAdress.setInt(1, cityId);
			insertIntoAdress.setString(2, street);
			insertIntoAdress.setInt(3, postalCode);

			insertIntoAdress.executeUpdate();

			ResultSet rs = insertIntoAdress.getGeneratedKeys();
			int index = 0;
			if (rs.next()){
				index=rs.getInt(1);
			}

			return index;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return AbstractDAO.INVALID_QUERY;
	}

}

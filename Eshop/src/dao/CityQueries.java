package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.City;

public class CityQueries extends AbstractDAO {	
	private static final String SELECT_ALL_CITIES = "SELECT * FROM cities";
	private static final String SELECT_CITY_BY_ID = "SELECT * FROM cities WHERE id = ?";
	
	private PreparedStatement selectAllCities = null;
	private PreparedStatement selectCityById = null;
	
	// get All Cities
	public List<City> showAllCities() {
		List<City> cityList = new ArrayList<City>();
		ResultSet resultSet = null;
		
		try {
			selectAllCities = getConnection().prepareStatement(SELECT_ALL_CITIES);
			resultSet = selectAllCities.executeQuery();
			while (resultSet.next()) {
				City city = new City(resultSet.getInt(1), resultSet.getString(2));
				cityList.add(city);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return cityList;
	}
	
	//Get City by ID
	public City selectUserById(int id) {
		ResultSet resultSet = null;
		City city = null;

		try {
			selectCityById = getConnection().prepareStatement(SELECT_CITY_BY_ID);

			selectCityById.setInt(1, id);

			resultSet = selectCityById.executeQuery();
			while (resultSet.next()) {
				city = new City(resultSet.getInt(1), resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return city;

	}

}

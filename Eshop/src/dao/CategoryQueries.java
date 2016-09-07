package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.Category;

public class CategoryQueries extends AbstractDAO {
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM categories";

	private PreparedStatement selectAllCategories = null;

	// get All Categories
	public List<Category> showAllCategories() {
		List<Category> categoriesList = new ArrayList<Category>();
		ResultSet resultSet = null;

		try {
			selectAllCategories = getConnection().prepareStatement(SELECT_ALL_CATEGORIES);
			resultSet = selectAllCategories.executeQuery();
			while (resultSet.next()) {
				Category category = new Category(resultSet.getInt(1), resultSet.getString(2));
				categoriesList.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categoriesList;
	}
}

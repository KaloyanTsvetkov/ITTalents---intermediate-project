package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import model.entity.Foto;
import model.entity.Product;

public class ProductQueries extends AbstractDAO {
	private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";
	private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE products.ID = ?";
	private static final String SELECT_PRODUCTS_BY_CATEGORY = "SELECT products.ID, PRODUCT_NAME, QUANTITY, CATEGORY_NAME, PRICE, DESCRIPTION"
			+ " FROM products INNER JOIN categories ON products.CATEGORY_ID = categories.ID WHERE categories.ID = ?";
	private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM products WHERE products.ID = ?";
	private static final String UPDATE_PRODUCT_BY_ID = "UPDATE products SET PRODUCT_NAME = ?, QUANTITY = ?, CATEGORY_ID = ?, PRICE = ?, "
			+ "DESCRIPTION = ? WHERE products.ID = ?";
	private static final String INSERT_PRODUCT = "INSERT INTO products (PRODUCT_NAME, QUANTITY, CATEGORY_ID, PRICE, DESCRIPTION) "
			+ "VALUES (?, ?, ?, ?, ?)";

	private PreparedStatement selectAllProducts = null;
	private PreparedStatement selectProductByID = null;
	private PreparedStatement selectProductByCategory = null;
	private PreparedStatement deleteProductByID = null;
	private PreparedStatement updateProductByID = null;
	private PreparedStatement insertProduct = null;

	public List<Product> getAllProducts() {
		List<Product> productsList = new ArrayList<Product>();
		ResultSet resultSet = null;

		try {
			selectAllProducts = getConnection().prepareStatement(SELECT_ALL_PRODUCTS);
			resultSet = selectAllProducts.executeQuery();
			while (resultSet.next()) {
				FotoQueries fotoQ = new FotoQueries();
				List<Foto> fotos = fotoQ.getFotosByProductId(resultSet.getInt(1));

				Product product = new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(5),
						resultSet.getString(6), resultSet.getInt(3));

				for (Foto foto : fotos) {
					if (foto != null) {
						product.addFoto(foto);
					}
				}

				productsList.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productsList;
	}

	public Product getProductByID(int id) {
		Product product = null;
		ResultSet resultSet = null;

		try {
			selectProductByID = getConnection().prepareStatement(SELECT_PRODUCT_BY_ID);
			selectProductByID.setInt(1, id);
			resultSet = selectProductByID.executeQuery();
			while (resultSet.next()) {
				product = new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(5),
						resultSet.getString(6), resultSet.getInt(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	// SELECT Products by category ID
	public List<Product> getProductsByCategory(int categoryId) {
		List<Product> productsList = new ArrayList<Product>();
		ResultSet resultSet = null;

		try {
			selectProductByCategory = getConnection().prepareStatement(SELECT_PRODUCTS_BY_CATEGORY);
			selectProductByCategory.setInt(1, categoryId);
			resultSet = selectProductByCategory.executeQuery();
			while (resultSet.next()) {
				FotoQueries fotoQ = new FotoQueries();
				List<Foto> fotos = fotoQ.getFotosByProductId(resultSet.getInt(1));

				Product product = new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(5),
						resultSet.getString(6), resultSet.getInt(3));

				for (Foto foto : fotos) {
					if (foto != null) {
						product.addFoto(foto);
					}
				}

				productsList.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productsList;
	}

	public int removeProductByID(int id) {
		int result = -1;
		try {

			FotoQueries fotoQueries = new FotoQueries();
			fotoQueries.removeFotoByProductID(id);

			deleteProductByID = getConnection().prepareStatement(DELETE_PRODUCT_BY_ID);
			deleteProductByID.setInt(1, id);
			result = deleteProductByID.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public boolean updateProductByID(int id, String productName, int quantity, int category, double price,
			String descriptio) {
		int result = 0;
		boolean flag = true;
		Connection connection = getConnection();

		try {
			connection.setAutoCommit(false); // Start transaction
			updateProductByID = getConnection().prepareStatement(UPDATE_PRODUCT_BY_ID);

			// set parameters, then executing
			updateProductByID.setString(1, productName);
			updateProductByID.setInt(2, quantity);
			updateProductByID.setInt(3, category);
			updateProductByID.setDouble(4, price);
			updateProductByID.setString(5, descriptio);
			updateProductByID.setInt(6, id);

			// exequte updateSeatsByID query
			result = updateProductByID.executeUpdate();

			if (result != 1) {
				connection.rollback(); // If can't Update Info RollBack
				flag = false;
			}

			connection.commit(); // End transaction
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag; // return statement
	}

	public int addNewProduct(String productName, int quantity, int category, double price, String description,
			String fotoPath) {
		int productId = 0;

		try {
			updateProductByID = getConnection().prepareStatement(UPDATE_PRODUCT_BY_ID);

			insertProduct = getConnection().prepareStatement(INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);

			insertProduct.setString(1, productName);
			insertProduct.setInt(2, quantity);
			insertProduct.setInt(3, category);
			insertProduct.setDouble(4, price);
			insertProduct.setString(5, description);
			insertProduct.executeUpdate();

			ResultSet rs = insertProduct.getGeneratedKeys();
			if (rs.next()) {
				productId = rs.getInt(1);
			}

			FotoQueries fotoQueries = new FotoQueries();
			int fotoId = fotoQueries.addNewFotro(fotoPath);

			fotoQueries.addFotoToProduct(productId, fotoId);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productId;
	}

}

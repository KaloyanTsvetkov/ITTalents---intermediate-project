package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import model.entity.Foto;

public class FotoQueries extends AbstractDAO {
	private static final String SELECT_FOTOS_BY_PRODUCT_ID = "SELECT ID, PICTURE_PATH FROM fotos INNER JOIN fotos_products ON fotos.id = fotos_products.foto_id WHERE fotos_products.product_id = ?";
	private static final String DELETE_FOTO_BY_PRODUCT_ID = "DELETE FROM products WHERE products.ID = ?";
	private static final String INSERT_FOTO = "INSERT INTO fotos (PICTURE_PATH) VALUES (?)";
	private static final String INSERT_FOTO_PRODUCT_BY_ID = "INSERT INTO fotos_products (foto_id, product_id) VALUES (?, ?)";

	private PreparedStatement selectFotosByID = null;
	private PreparedStatement deleteFotoByProductID = null;
	private PreparedStatement insertFoto = null;
	private PreparedStatement insertFotoProductByID = null;

	public List<Foto> getFotosByProductId(int prodId) {
		List<Foto> fotosList = new ArrayList<Foto>();
		ResultSet resultSet = null;

		try {
			selectFotosByID = getConnection().prepareStatement(SELECT_FOTOS_BY_PRODUCT_ID);
			selectFotosByID.setInt(1, prodId);
			resultSet = selectFotosByID.executeQuery();
			while (resultSet.next()) {

				Foto foto = new Foto(resultSet.getInt(1), resultSet.getString(2));
				fotosList.add(foto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fotosList;
	}

	public int removeFotoByProductID(int id) {
		int result = 0;

		try {
			deleteFotoByProductID = getConnection().prepareStatement(DELETE_FOTO_BY_PRODUCT_ID);
			deleteFotoByProductID.setInt(1, id);
			result = deleteFotoByProductID.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int addNewFotro(String fotoPath) {
		int resultId = 0;
		try {
			insertFoto = getConnection().prepareStatement(INSERT_FOTO, Statement.RETURN_GENERATED_KEYS);

			insertFoto.setString(1, fotoPath);
			insertFoto.executeUpdate();

			ResultSet rs = insertFoto.getGeneratedKeys();
			if (rs.next()) {
				resultId = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultId;
	}

	public boolean addFotoToProduct(int productID, int fotoID) {
		int resultId = 0;
		try {
			insertFotoProductByID = getConnection().prepareStatement(INSERT_FOTO_PRODUCT_BY_ID);

			insertFotoProductByID.setInt(1, fotoID);
			insertFotoProductByID.setInt(2, productID);
			resultId = insertFotoProductByID.executeUpdate();

			if (resultId < 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}

package model.entity;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AdressQueries;
import dao.CityQueries;
import dao.CustomerQueries;
import dao.ProductQueries;
import dao.UserQueries;

public class Demo {

	public static void main(String[] args) {
		File fail = new File("pics\\lego_1.jpg");

		System.out.println(fail.getAbsolutePath());
		System.out.println(fail.getName());

		List<City> cities = new ArrayList<City>();
		CityQueries querie = new CityQueries();
		cities = querie.showAllCities();

		for (City city : cities) {
			System.out.println(city.getCityName());
		}

		UserQueries userQ = new UserQueries();
		User user = userQ.selectUserById(1);
		System.out.println(user);

		AdressQueries adrQ = new AdressQueries();
		Adress adress = adrQ.selectAdressById(2);
		System.out.println(adress.getStreet());

		CustomerQueries uQ = new CustomerQueries();
		int i = -5;

		try {
			i = uQ.addNewCustomer("rori", "rori", "rori", "rori", "rori", "rori@aqbv.bg", 1, "Kiril roriVVV 21",
					1212);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		System.out.println(i);
		
		ProductQueries fotoQ = new ProductQueries();
		List<Product> fotos = new ArrayList<Product>();
		fotos = fotoQ.getAllProducts();
		for (Product foto : fotos) {
			System.out.println(foto.getId() + foto.getProductName() + foto.getPrice());
			for (Foto productFoto : foto.getFotos()) {
				System.out.println(productFoto.getPicturePath());
			}
		}
		
		System.out.println("=========================");
		
		ProductQueries prodQueries = new ProductQueries();
		fotos = prodQueries.getProductsByCategory(5);
		for (Product foto : fotos) {
			System.out.println(foto.getId() + foto.getProductName() + foto.getPrice() + foto.getQuantity() + foto.getDescription());
			for (Foto productFoto : foto.getFotos()) {
				System.out.println(productFoto.getPicturePath());
			}
		}

	}

}

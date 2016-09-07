package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;
	private double price;
	private String productName;
	private int quantity;
	private List<Foto> fotos;
	private List<Order> orders;
	private Category category;

	public Product() {
	}

	public Product(int id, String productName, double price, String description, int quantity) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.productName = productName;
		this.quantity = quantity;
		this.fotos = new ArrayList<Foto>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addFoto(Foto foto) {
		if (foto != null) {
			this.fotos.add(foto);
		}
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setProduct(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setProduct(null);

		return order;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Foto> getFotos() {
		return this.fotos;
	}

}
package model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class History implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private Date keyV;
	private String status;
	private List<Cart> carts;
	private User user;

	public History() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getKeyV() {
		return this.keyV;
	}

	public void setKeyV(Date keyV) {
		this.keyV = keyV;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setHistory(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setHistory(null);

		return cart;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
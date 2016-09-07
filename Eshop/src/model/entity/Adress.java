package model.entity;

import java.io.Serializable;
import java.util.List;

public class Adress implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int postalCode;
	private String street;
	private City city;
	private List<Owner> owners;

	public Adress() {
	}

	public Adress(int id, String street, int postalCode, City city) {
		this.id = id;
		this.postalCode = postalCode;
		this.street = street;
		this.city = city;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Owner> getOwners() {
		return this.owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

	public Owner addOwner(Owner owner) {
		getOwners().add(owner);
		owner.setAdress(this);

		return owner;
	}

	public Owner removeOwner(Owner owner) {
		getOwners().remove(owner);
		owner.setAdress(null);

		return owner;
	}

}
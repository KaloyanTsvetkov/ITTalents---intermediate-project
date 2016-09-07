package model.entity;

import java.io.Serializable;

public class Owner implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String bic;
	private String companyName;
	private String eik;
	private String email;
	private String iban;
	private String mol;
	private String phoneNumber;
	private Adress adress;

	public Owner() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBic() {
		return this.bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEik() {
		return this.eik;
	}

	public void setEik(String eik) {
		this.eik = eik;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getMol() {
		return this.mol;
	}

	public void setMol(String mol) {
		this.mol = mol;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Adress getAdress() {
		return this.adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

}
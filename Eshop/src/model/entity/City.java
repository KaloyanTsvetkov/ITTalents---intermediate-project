package model.entity;

import java.io.Serializable;

public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String cityName;

	public City() {
	}

	public City(int id, String cityName) {
		this.id = id;
		this.cityName = cityName;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
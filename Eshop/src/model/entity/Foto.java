package model.entity;

import java.io.Serializable;

public class Foto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String picturePath;

	public Foto() {
	}	

	public Foto(int id, String picturePath) {
		this.id = id;
		this.picturePath = picturePath;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPicturePath() {
		return this.picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}


}
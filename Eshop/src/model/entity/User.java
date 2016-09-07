package model.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private boolean isAdmin;
	private String loginName;
	private String loginPassword;
	private String sault;
	private List<History> histories;

	public User() {
	}

	public User(int id, String loginName, String loginPassword, String sault, boolean isAdmin) {
		this.id = id;
		this.isAdmin = isAdmin;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.sault = sault;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getSault() {
		return this.sault;
	}

	public void setSault(String sault) {
		this.sault = sault;
	}

	public List<History> getHistories() {
		return this.histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setUser(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setUser(null);

		return history;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", isAdmin=" + isAdmin + ", loginName=" + loginName + ", loginPassword="
				+ loginPassword + ", sault=" + sault + "]";
	}
	
	

}
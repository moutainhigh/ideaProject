package hry.manage.remote.model;

import java.io.Serializable;

public class UserInfo implements  Serializable{
	
	//真实姓名
	private String trueName;
	//国家
	private String country;
	//证件类型
	private String cardType;
	//证件号
	private String cardId;
	
	private String surname;
	
	private String papersType;
	
	private String type;
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPapersType() {
		return papersType;
	}
	public void setPapersType(String papersType) {
		this.papersType = papersType;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	
	
	

}

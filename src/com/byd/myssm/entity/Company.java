package com.byd.myssm.entity;

public class Company {
	private int id;
	private String name;
	private String address;
	private String ceo;
	private String tel;
	private String code;
	private String phone;
	private String memo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "{\"id\":" + id + ", \"name\":\"" + name + "\", \"address\":\"" + address + "\", \"ceo\":\"" 
	+ ceo + "\", \"tel\":\"" + tel + "\", \"code\":\"" + code + "\",\"phone\":\"" + phone + 
	"\", \"memo\":\"" + memo + "\"}";
	}
}

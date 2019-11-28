package com.byd.myssm.entity;

public class Worker {
	private int id;
	private String name;
	private String tel;
	private String add;
	private String memo;
	@Override
	public String toString() {
		return "{\"id\":" + id + ", \"name\":\"" +
				name + "\", \"tel\":\"" + tel + "\", \"add\":\"" + add +
				"\", \"memo\":\"" + memo + "\"}";
	}
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}

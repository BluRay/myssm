package com.byd.myssm.entity;

public class Car {
	private int id;
	private String chepai;
	private String gongsi;
	private String siji;
	private String dianhua;
	private String anzhuangriqi;
	private String jijiaqihao;
	private String beizhu;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChepai() {
		return chepai;
	}
	public void setChepai(String chepai) {
		this.chepai = chepai;
	}
	public String getGongsi() {
		return gongsi;
	}
	public void setGongsi(String gongsi) {
		this.gongsi = gongsi;
	}
	public String getSiji() {
		return siji;
	}
	public void setSiji(String siji) {
		this.siji = siji;
	}
	public String getDianhua() {
		return dianhua;
	}
	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}
	public String getAnzhuangriqi() {
		return anzhuangriqi;
	}
	public void setAnzhuangriqi(String anzhuangriqi) {
		this.anzhuangriqi = anzhuangriqi;
	}
	public String getJijiaqihao() {
		return jijiaqihao;
	}
	public void setJijiaqihao(String jijiaqihao) {
		this.jijiaqihao = jijiaqihao;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	@Override
	public String toString() {
		return "{\"id\":" + id + ", \"chepai\":\"" + chepai + "\", \"gongsi\":\"" +
	gongsi + "\", \"siji\":\"" + siji + "\", \"dianhua\":\"" + dianhua +
	"\", \"anzhuangriqi\":\"" + anzhuangriqi + "\", \"jijiaqihao\":\"" + jijiaqihao + "\", \"beizhu\":" + beizhu + "}";
	}
}

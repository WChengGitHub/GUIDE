package com.data.model;

import java.sql.Timestamp;

public class AreaAdminAddOrDeleteSpotModel {
	private String Account;
	private String Sid;
	private String Spot;
	private String Longitude;
	private String Latitude;
	private String spotAdmin;
	private String CreateTime;
	private String spotAdminDel;
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getSpot() {
		return Spot;
	}
	public void setSpot(String spot) {
		Spot = spot;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getSpotAdmin() {
		return spotAdmin;
	}
	public void setSpotAdmin(String spotAdmin) {
		this.spotAdmin = spotAdmin;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getSpotAdminDel() {
		return spotAdminDel;
	}
	public void setSpotAdminDel(String spotAdminDel) {
		this.spotAdminDel = spotAdminDel;
	}
	
}

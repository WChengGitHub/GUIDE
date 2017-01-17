package com.data.model;

public class tb_areaModel {
	private String Arid;
	private String Area;
	private String Cid;
	private String Longitude;
	private String Latitude;
	private int radius;
	
	public int getRadius() {
		return radius;
	}
	public void setRadius(int raidus) {
		this.radius = raidus;
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
	public String getArid() {
		return Arid;
	}
	public void setArid(String arid) {
		Arid = arid;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getCid() {
		return Cid;
	}
	public void setCid(String cid) {
		Cid = cid;
	}
	
}

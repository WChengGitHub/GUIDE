package com.data.model;

public class tb_adviceModel {
	String ADid;
	String Title;
	String Advice;
	String type;
	String Atime;
	String Status;
	String Vid;
	public String getADid() {
		return ADid;
	}
	public void setADid(String aDid) {
		ADid = aDid;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getAdvice() {
		return Advice;
	}
	public void setAdvice(String advice) {
		Advice = advice;
	}
	public String getType() {
		return type;
	}
	public String getAtime() {
		return Atime;
	}
	public void setAtime(String atime) {
		Atime = atime;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getVid() {
		return Vid;
	}
	public void setVid(String vid) {
		Vid = vid;
	}
	
}

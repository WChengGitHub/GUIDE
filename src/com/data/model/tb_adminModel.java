package com.data.model;

import com.sun.jmx.snmp.Timestamp;

public class tb_adminModel {
	private String Aid;
	private String Account;
	private String Password;
	private String Privilege;
	private String Sid;
	private String CreateTime;
	public String getAid() {
		return Aid;
	}
	public void setAid(String aid) {
		Aid = aid;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPrivilege() {
		return Privilege;
	}
	public void setPrivilege(String privilege) {
		Privilege = privilege;
	}
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String string) {
		CreateTime = string;
	}
	

	
}

package com.data.model;

import java.sql.Timestamp;

public class tb_replyModel {
	private String Rid;
	private String Title;
	private String Reply;
	private String ADid;
	private Timestamp Time;
	private String Aid;

	public String getRid() {
		return Rid;
	}

	public void setRid(String rid) {
		Rid = rid;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getReply() {
		return Reply;
	}

	public void setReply(String reply) {
		Reply = reply;
	}

	public String getADid() {
		return ADid;
	}

	public void setADid(String aDid) {
		ADid = aDid;
	}

	public Timestamp getTime() {
		return Time;
	}

	public void setTime(Timestamp time) {
		Time = time;
	}

	public String getAid() {
		return Aid;
	}

	public void setAid(String aid) {
		Aid = aid;
	}
}

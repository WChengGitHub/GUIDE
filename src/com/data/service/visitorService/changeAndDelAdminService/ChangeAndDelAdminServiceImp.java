package com.data.service.visitorService.changeAndDelAdminService;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.tb_adminDao;
import com.data.dao.singleForm.tb_spotDao;
import com.data.model.ChangeAndDelAdminModel;
import com.data.service.SendEmail;

public class ChangeAndDelAdminServiceImp implements ChangeAndDelAdminService {
	private tb_adminDao adminDao;
	private tb_spotDao spotDao;
	private List<Object> param;
	private List<Object> adminRecordList;

	public tb_spotDao getSpotDao() {
		return spotDao;
	}

	public void setSpotDao(tb_spotDao spotDao) {
		this.spotDao = spotDao;
	}

	public tb_adminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(tb_adminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public List<Object> getadminRecord() {
		String sql = "select Account,Privilege,Email,CreateTime,Aid from tb_admin where Del=? And Privilege!=?";
		param = new LinkedList<Object>();
		param.add("0");
		param.add("a");
		adminRecordList = new LinkedList<Object>();
		try {
			adminRecordList = adminDao.queryRecord(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("记录获取失败，请检查sql语句是否正确");
			return null;
		}
		if (adminRecordList == null) {
			System.out.println("数据库中没有该记录");
			return null;
		}
		// TODO Auto-generated method stub
		return adminRecordList;
	}

	@Override
	public boolean setAdminDelStatus(
			ChangeAndDelAdminModel changeAndDelAdminModel) {
		String sql = "update tb_admin set Del=?,DelTime=? where Aid=?";

		Timestamp Time = new Timestamp(System.currentTimeMillis());
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Time = Timestamp.valueOf(sdf1.format(Time));
		param = new LinkedList<Object>();
		param.add("1");
		param.add(Time);
		param.add(changeAndDelAdminModel.getAid());
		try {
			adminDao.update(sql, param);

		} catch (Exception e) {
			// TODO: handle exception
			System.out
					.println("changeAndDelAdmin:setAdminDelStatus:更新失败，请检查sql语句是否正确");
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ChangeAndDelAdminModel getSid(
			ChangeAndDelAdminModel changeAndDelAdminModel) {
		String sql = "select Sid from tb_spot where spot=\""
				+ changeAndDelAdminModel.getSpot() + "\"";
		try {
			changeAndDelAdminModel = spotDao.querySid(sql);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ChangeDelAdminServiceImp getSid:查询Sid失败");
		}

		if (changeAndDelAdminModel == null) {
			System.out.println("ChangeDelAdminServiceImp getSid:数据库没有该记录");
			return null;
		}
		// TODO Auto-generated method stub
		return changeAndDelAdminModel;
	}

	@Override
	public boolean setChangeRecord(ChangeAndDelAdminModel changeAndDelAdminModel) {
		String Privilege = changeAndDelAdminModel.getPrivilege();
		if (!(Privilege.equals("s") || Privilege.equals("c") || Privilege
				.equals("")))
			return false;
		if (Privilege.equals("a")) {
			System.out.println("超级管理员不能增加超级管理员");
			return false;
		}
		System.out.println("Privilege:" + Privilege);
		String admin;
		if (Privilege.equals("s"))
			admin = "景区管理员";
		else
			admin = "审核管理员";
		String sql;

		param = new LinkedList<Object>();
		Timestamp Time = new Timestamp(System.currentTimeMillis());
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Time = Timestamp.valueOf(sdf1.format(Time));
		String Sid = getSid(changeAndDelAdminModel).getSid();
		if (Privilege.equals("") && Sid.length() == 0)
			return false;
		// System.out.println("44444444");
		if (Privilege.equals("")) {
			sql = "update tb_admin set ChangeTime=?,Sid=? where Aid=?";
			param.add(Time);
			param.add(Sid);
			param.add(changeAndDelAdminModel.getAid());
		} else if (Sid == null) {
			sql = "update tb_admin set Privilege=?,ChangeTime=? where Aid=?";
			param.add(changeAndDelAdminModel.getPrivilege());
			param.add(Time);
			param.add(changeAndDelAdminModel.getAid());
		} else {
			sql = "update tb_admin set Privilege=?,ChangeTime=?,Sid=? where Aid=?";
			param.add(changeAndDelAdminModel.getPrivilege());
			param.add(Time);
			param.add(Sid);
			param.add(changeAndDelAdminModel.getAid());
		}
		System.out.println(changeAndDelAdminModel.getPrivilege() + " " + Time
				+ " " + Sid + " " + changeAndDelAdminModel.getAid());
		try {
			adminDao.update(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			System.out
					.println("changeAndDelAdmin:setAdminDelStatus:更新失败，请检查sql语句是否正确");
			return false;
		}
		// TODO Auto-generated method stub
		try {
			SendEmail.sendEmail(changeAndDelAdminModel.getEmail(), "超级管理员信件",
					"尊敬的" + changeAndDelAdminModel.getAccount() + "，您的已经变成了"
							+ admin);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("邮件发送异常");
		}
		return true;
	}

}

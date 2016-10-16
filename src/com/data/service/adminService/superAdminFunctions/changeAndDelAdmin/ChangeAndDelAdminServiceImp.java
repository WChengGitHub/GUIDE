package com.data.service.adminService.superAdminFunctions.changeAndDelAdmin;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.tb_adminDao;
import com.data.dao.singleForm.tb_areaDao;
import com.data.dao.singleForm.tb_cityDao;
import com.data.dao.singleForm.tb_provinceDaoImp;
import com.data.dao.singleForm.tb_spotDao;
import com.data.model.ChangeAndDelAdminModel;
import com.data.service.SendEmail;

public class ChangeAndDelAdminServiceImp implements ChangeAndDelAdminService {
	private tb_adminDao adminDao;
	private tb_provinceDaoImp provinceDaoImp;
	private tb_cityDao cityDao;
	private tb_areaDao areaDao;
	private tb_spotDao spotDao;
	private List<Object> param;
	private List<Object> list;

	public tb_cityDao getCityDao() {
		return cityDao;
	}

	public void setCityDao(tb_cityDao cityDao) {
		this.cityDao = cityDao;
	}

	public tb_areaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(tb_areaDao areaDao) {
		this.areaDao = areaDao;
	}

	public tb_provinceDaoImp getProvinceDaoImp() {
		return provinceDaoImp;
	}

	public void setProvinceDaoImp(tb_provinceDaoImp provinceDaoImp) {
		this.provinceDaoImp = provinceDaoImp;
	}

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

	// 用来获得审核管理员和景区管理的Account,Privilege,Email,CreateTime,Aid，0代表还未删除，a代表超级管理员
	@Override
	public List<Object> getAdminRecord() {
		String sql = "select Account,Privilege,Email,CreateTime,Aid from tb_admin where Del=? And Privilege!=?";
		param = new LinkedList<Object>();
		param.add("0");
		param.add("a");
		list = new LinkedList<Object>();
		try {
			list = adminDao.queryRecord(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

		// TODO Auto-generated method stub
		return list;
	}

	// 删除景区管理员或审核管理员，并给相应管理员发送通知，需要Aid,Email,Account
	@Override
	public boolean deleteAdmin(ChangeAndDelAdminModel changeAndDelAdminModel) {
		String sql = "update tb_admin set Del=?,DelTime=? where Aid=?";
		String Aid = changeAndDelAdminModel.getAid();
		String Email = changeAndDelAdminModel.getEmail();
		String Account = changeAndDelAdminModel.getAccount();
		Timestamp Time;
		boolean sign = false;
		Time = new Timestamp(System.currentTimeMillis());
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Time = Timestamp.valueOf(sdf1.format(Time));
		param = new LinkedList<Object>();
		param.add("1");
		param.add(Time);
		param.add(Aid);
		try {
			adminDao.update(sql, param);
			sign = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (sign == true) {
			try {
				SendEmail.sendEmail(Email, "超级管理员信件", "尊敬的" + Account
						+ "，您的管理员权限已被取消");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
		}
		// TODO Auto-generated method stub
		return sign;
	}
    //修改管理员权限、改变管理管理的景区、给相应管理员发邮件，需要传入的参数：Arid,Aid,Email,Account
	@Override
	public boolean changeAdmin(ChangeAndDelAdminModel changeAndDelAdminModel) {
		String Privilege = changeAndDelAdminModel.getPrivilege();
		String admin;
		String sql="";
		String Arid= changeAndDelAdminModel.getArid();
		String Aid=changeAndDelAdminModel.getAid();
		String Email=changeAndDelAdminModel.getEmail();
		String Account=changeAndDelAdminModel.getAccount();
		boolean sign = false;
		Timestamp Time;
		if ((!Privilege.isEmpty())&& Privilege.equals("r"))
			admin = "景区管理员";
		else
			admin = "审核管理员";

		param = new LinkedList<Object>();

		Time = new Timestamp(System.currentTimeMillis());
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Time = Timestamp.valueOf(sdf1.format(Time));
		//把管理员权限变为r(景区管理员），改变管理景区
		if(!(Arid.isEmpty()))
		{
			
			sql = "update tb_admin set Privilege=?,ChangeTime=?,Arid=? where Aid=?";
			param.add("r");
			param.add(Time);
			param.add(Arid);
			param.add(Aid);
		
		
		} else if (Privilege.equals("c")) {
			//把管理员权限变为c
			sql = "update tb_admin set Privilege=?,ChangeTime=?,Arid=NULL,Sid=NULL where Aid=?";
			param.add(changeAndDelAdminModel.getPrivilege());
			param.add(Time);
			param.add(Aid);
			
		} 
		try {
			adminDao.update(sql, param);
			sign = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		// TODO Auto-generated method stub
		try {
			SendEmail.sendEmail(Email, "超级管理员信件",
					"尊敬的" + Account+ "，您的已经变成了"
							+ admin);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return sign;
	}

	@Override
	public List<Object> getProvinces() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_province";

		param = new LinkedList<Object>();
		try {
			list = provinceDaoImp.query(sql, param);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
//需要传入参数Pid
	@Override
	public List<Object> getCities(ChangeAndDelAdminModel changeAndDelAdminModel) {
		String sql = "select Cid,City from tb_city where Pid=?";
		param = new LinkedList<Object>();
		param.add(changeAndDelAdminModel.getPid());
		try {
			list = cityDao.query(sql, param);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
//需要传入参数Arid
	@Override
	public List<Object> getAreas(ChangeAndDelAdminModel changeAndDelAdminModel) {
		// TODO Auto-generated method stub
		String sql = "select Arid,Area from tb_area where Cid=?";
		param = new LinkedList<Object>();
		param.add(changeAndDelAdminModel.getCid());
		try {
			list = areaDao.query(sql, param);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}

}

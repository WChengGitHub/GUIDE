package com.data.service.adminService.superAdminFunctions.changeAndDelAdmin;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.tb_adminDao;
import com.data.dao.singleForm.tb_areaDao;
import com.data.dao.singleForm.tb_cityDao;
import com.data.dao.singleForm.tb_provinceDao;
import com.data.dao.singleForm.tb_provinceDaoImp;
import com.data.dao.singleForm.tb_spotDao;
import com.data.model.ChangeAndDelAdminModel;
import com.data.model.tb_provinceModel;
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

	@Override
	public List<Object> getadminRecord() {
		String sql = "select Account,Privilege,Email,CreateTime,Aid from tb_admin where Del=? And Privilege!=?";
		param = new LinkedList<Object>();
		param.add("0");
		param.add("a");
		list = new LinkedList<Object>();
		try {
			list = adminDao.queryRecord(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("记录获取失败，请检查sql语句是否正确");
			return null;
		}
		if (list == null) {
			System.out.println("数据库中没有该记录");
		}
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public boolean setAdminDelStatus(
			ChangeAndDelAdminModel changeAndDelAdminModel) {
		String sql = "update tb_admin set Del=?,DelTime=? where Aid=?";
        boolean sign=false;
		Timestamp Time = new Timestamp(System.currentTimeMillis());
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Time = Timestamp.valueOf(sdf1.format(Time));
		param = new LinkedList<Object>();
		param.add("1");
		param.add(Time);
		param.add(changeAndDelAdminModel.getAid());
		try {
			adminDao.update(sql, param);
            sign=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(sign==true)
		{
			try {
				SendEmail.sendEmail(changeAndDelAdminModel.getEmail(), "超级管理员信件", "尊敬的" + changeAndDelAdminModel.getAccount()
						+ "，您的管理员权限已被取消");
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("邮件发送异常");
				
			}
		}
		// TODO Auto-generated method stub
		return sign;
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
			e.printStackTrace();
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
		boolean sign=false;
		/*if (!(Privilege.equals("s") || Privilege.equals("c") || Privilege
				.equals("")))
			return false;
		if (Privilege.equals("a")) {
			System.out.println("超级管理员不能增加超级管理员");
			return false;
		}
		System.out.println("Privilege:" + Privilege);*/
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
			sign=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
		return sign;
	}

	@Override
	public List<Object> getProvinceModel() {
		// TODO Auto-generated method stub
		String sql="select * from tb_province";
		
		param = new LinkedList<Object>();
		try {
			list=provinceDaoImp.query(sql,param);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ChangeAndDelAdminService:getProvinceModel:查询出现错误");
		}
		if(list==null)
		{
			System.out.println("数据库没有改纪录");
		}
		return list;
	}

	@Override
	public List<Object> getCity(ChangeAndDelAdminModel changeAndDelAdminModel) {
        String sql="select Cid,City from tb_city where Pid=?";
        param=new LinkedList<Object>();
        param.add(changeAndDelAdminModel.getPid());
        try {
			list=cityDao.query(sql,param);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ChangeAndDelAdminService:getCity:查询出现错误");
		}
		if(list==null)
		{
			System.out.println("数据库没有改纪录");
		}
		return list;
	}

	@Override
	public List<Object> getArea(ChangeAndDelAdminModel changeAndDelAdminModel) {
		// TODO Auto-generated method stub
		String sql="select Arid,Area from tb_area where Cid=?";
        param=new LinkedList<Object>();
        param.add(changeAndDelAdminModel.getCid());
        try {
			list=areaDao.query(sql,param);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ChangeAndDelAdminService:getArea:查询出现错误");
		}
		if(list==null)
		{
			System.out.println("数据库没有改纪录");
		}
		return list;
	}

	@Override
	public List<Object> getSpot(ChangeAndDelAdminModel changeAndDelAdminModel) {
		// TODO Auto-generated method stub
		String sql="select Spot from tb_spot where Arid=?";
        param=new LinkedList<Object>();
        param.add(changeAndDelAdminModel.getArid());
        try {
			list=spotDao.query(sql,param);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ChangeAndDelAdminService:getSpot:查询出现错误");
		}
		if(list==null)
		{
			System.out.println("数据库没有改纪录");
		}
		return list;
	}
	

}

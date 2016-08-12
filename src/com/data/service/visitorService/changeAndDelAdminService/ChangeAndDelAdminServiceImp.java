package com.data.service.visitorService.changeAndDelAdminService;

import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.tb_adminDao;

public class ChangeAndDelAdminServiceImp implements ChangeAndDelAdminService{
	private tb_adminDao adminDao;
	private List<Object> param;
	private List<Object>adminRecordList;

	public tb_adminDao getAdminDao() {
		return adminDao;
	}


	public void setAdminDao(tb_adminDao adminDao) {
		this.adminDao = adminDao;
	}


	@Override
	public List<Object> getadminRecord() {
		String sql="select Account,Privilege,Email,CreateTime,Aid from tb_admin where Del=? And Privilege!=?";
		param=new LinkedList<Object>();
		param.add("0");
		param.add("a");
		adminRecordList=new LinkedList<Object>();
		try {
			adminRecordList=adminDao.queryRecord(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("记录获取失败，请检查sql语句是否正确");
			return null;
		}
		if(adminRecordList==null)
		{
			System.out.println("数据库中没有该记录");
			return null;
		}
		// TODO Auto-generated method stub
		return adminRecordList;
	}


	@Override
	public boolean setAdminDelStatus(String Aid) {
		String sql="update tb_admin set Del=? where Aid=?";
		param=new LinkedList<Object>();
		param.add("1");
		param.add(Aid);
		try {
			adminDao.update(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("changeAndDelAdmin:setAdminDelStatus:更新失败，请检查sql语句是否正确");
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

}

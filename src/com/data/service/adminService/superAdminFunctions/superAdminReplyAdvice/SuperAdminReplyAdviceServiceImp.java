package com.data.service.adminService.superAdminFunctions.superAdminReplyAdvice;

import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;

import com.data.dao.singleForm.tb_adminDao;
import com.data.dao.singleForm.tb_adviceDao;
import com.data.model.SuperAdminReplyAdviceModel;
import com.data.model.tb_adminModel;

public class SuperAdminReplyAdviceServiceImp implements SuperAdminReplyAdviceService{
	private tb_adminDao adminDao;
	private tb_adviceDao adviceDao;
	private List<Object>list;
	private List<Object>param;

	public tb_adminDao getAdminDao() {
		return adminDao;
	}


	public void setAdminDao(tb_adminDao adminDao) {
		this.adminDao = adminDao;
	}
    

	

    
	public tb_adviceDao getAdviceDao() {
		return adviceDao;
	}


	public void setAdviceDao(tb_adviceDao adviceDao) {
		this.adviceDao = adviceDao;
	}


	@Override
	public int getSoftwareAdviceNumber(SuperAdminReplyAdviceModel superAdminReplyAdviceModel) {

		String Account=superAdminReplyAdviceModel.getAccount();
		String sql="select count(*) from tb_admin_advice where Aid in(Select Aid from tb_admin where Account=\""+Account+"\") and Status=\"0\"";
		int softwareAdviceNumber=-1;
		if(Account==null)
		{
			System.out.println("SuperAdminReplyAdviceServiceImp getSoftwareAdviceNumber Account为空值");
			return softwareAdviceNumber;
		}
		// TODO Auto-generated method stub
		try {
			softwareAdviceNumber=adminDao.queryRecordNumber(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return softwareAdviceNumber;
	}


	@Override
	public JSONArray getSoftwareAdvices(
			SuperAdminReplyAdviceModel superAdminReplyAdviceModel) {
		String Account=superAdminReplyAdviceModel.getAccount();
		if(Account==null||Account.length()==0)
		{
			System.out.println("SuperAdminReplyAdviceServiceImp getSoftwareAdvices:Account为空值");
			return null;
		}
		System.out.println("Account:"+Account);
		String sql="select ADid,Title,Atime,type,Advice,Vid from tb_advice where ADid in(select ADid from tb_admin_advice where Status=? and Aid in(select Aid from tb_admin where Account=?))";
		param=new LinkedList<Object>();
		param.add("0");
		param.add(Account);
		try {
			list=adviceDao.query(sql, param);
			JSONArray jsonArray=JSONArray.fromObject(list);
			// TODO Auto-generated method stub
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}


	

}

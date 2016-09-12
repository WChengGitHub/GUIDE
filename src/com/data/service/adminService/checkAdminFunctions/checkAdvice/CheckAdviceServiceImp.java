package com.data.service.adminService.checkAdminFunctions.checkAdvice;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.tb_adminDao;
import com.data.dao.singleForm.tb_admin_adviceDao;
import com.data.dao.singleForm.tb_adviceDao;
import com.data.model.CheckAdviceModel;
import com.data.model.tb_adminModel;

public class CheckAdviceServiceImp implements CheckAdviceService {
	private tb_adviceDao adviceDao;
	private tb_adminDao adminDao;
	private tb_admin_adviceDao admin_adviceDao;
	private List<Object> param;
	private List<Object> list;

	public tb_adviceDao getAdviceDao() {
		return adviceDao;
	}

	public void setAdviceDao(tb_adviceDao adviceDao) {
		this.adviceDao = adviceDao;
	}

	public tb_adminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(tb_adminDao adminDao) {
		this.adminDao = adminDao;
	}

	public tb_admin_adviceDao getAdmin_adviceDao() {
		return admin_adviceDao;
	}

	public void setAdmin_adviceDao(tb_admin_adviceDao admin_adviceDao) {
		this.admin_adviceDao = admin_adviceDao;
	}

	@Override
	public int getAdviceNumber(CheckAdviceModel checkAdviceModel) {
		int amount = 0;
		String sql = "";
		String Privilege = "";
		Privilege = checkAdviceModel.getPrivilege();
		sql = "select  count(*) from tb_advice where Status=\"0\"";
		try {
			amount = adviceDao.queryRecordNumber(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return amount;
	}

	@Override
	public List<Object> getAdvices() {
		String sql = "select ADid,Title,Atime,type,Advice,Vid from tb_advice where Status=?";
		param = new LinkedList<Object>();
		param.add("0");

		try {

			list = adviceDao.query(sql, param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (list == null) {
			System.out.println("建议表没有记录");
			return null;
		} else {
			return list;
		}
	}

	@Override
	public List<Object> getSuperAdminAids() {
		// TODO Auto-generated method stub
		String sql = "select Aid from tb_admin where Privilege=?";
		param = new LinkedList<Object>();
		param.add("a");
		try {
			list = adminDao.queryAids(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		if (list != null)
			return list;
		else {
			System.out.println("数据库上没有记录");
			return null;
		}

	}

	/* 这个是用来给管理员分配建议的函数 */
	@Override
	public boolean setAdminAdvice(CheckAdviceModel checkAdviceModel) {
		String Privilege=checkAdviceModel.getPrivilege();
		String ADid=checkAdviceModel.getADid();
		tb_adminModel adminModel;
		String sql="insert into tb_admin_advice values(?,?,?)";
		List<Object>Aids;
		if(Privilege.equals("s"));
		{
			Aids=getSuperAdminAids();
		}
		//System.out.println("Aids.size():"+Aids.size());
		if(Aids==null)
			return false;
		try {
			for (int i = 0; i < Aids.size(); i++) {
				adminModel=(tb_adminModel) Aids.get(i);
				param=new LinkedList<Object>();
				param.add(ADid);
				param.add(adminModel.getAid());
				param.add("0");
//				System.out.println("adminModel.getAid():"+adminModel.getAid());
//				System.out.println(param.get(0)+" "+param.get(1));
				admin_adviceDao.update(sql,param);
				
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return false;
	}

}

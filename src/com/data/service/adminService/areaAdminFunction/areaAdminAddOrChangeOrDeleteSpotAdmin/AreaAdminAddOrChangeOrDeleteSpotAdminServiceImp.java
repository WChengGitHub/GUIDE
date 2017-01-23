package com.data.service.adminService.areaAdminFunction.areaAdminAddOrChangeOrDeleteSpotAdmin;

import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.data.dao.singleForm.tb_adminDao;
import com.data.dao.singleForm.tb_spotDao;
import com.data.model.AreaAdminAddOrChangeOrDeleteSpotAdminModel;
import com.data.model.AreaAdminAddOrDeleteSpotModel;
import com.data.model.tb_adminModel;
import com.data.model.tb_spotModel;

public class AreaAdminAddOrChangeOrDeleteSpotAdminServiceImp implements AreaAdminAddOrChangeOrDeleteSpotAdminService{
	private tb_adminDao adminDao;
	private tb_spotDao spotDao;
	private List<Object>param;
	private List<Object>list;
	
	public tb_adminDao getAdminDao() {
		return adminDao;
	}


	public void setAdminDao(tb_adminDao adminDao) {
		this.adminDao = adminDao;
	}


	public tb_spotDao getSpotDao() {
		return spotDao;
	}


	public void setSpotDao(tb_spotDao spotDao) {
		this.spotDao = spotDao;
	}


	//根据景区管理员Account得到景区管理员所在景区的景点管理员的Account,Aid,以及景点管理员所管理景点的Spot,Sid
	@Override
	public List<AreaAdminAddOrChangeOrDeleteSpotAdminModel> getSpotAdminInformations(
			AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel) {
		String sql="select Account,Aid,Sid from tb_admin where Sid in(select Sid from tb_spot where Arid =(select Arid from tb_admin where Account=?) and Status=?) and del=?";
		String Account=areaAdminAddOrChangeOrDeleteSpotAdminModel.getAccount();
		List<AreaAdminAddOrChangeOrDeleteSpotAdminModel> list1=new LinkedList<AreaAdminAddOrChangeOrDeleteSpotAdminModel>();
		param=new LinkedList<Object>();
		param.add(Account);
		param.add(1);
		param.add(0);
		try {
			list=adminDao.query2(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//JSONArray jsonArray = JSONArray.fromObject(list);
		//System.out.println("1:"+jsonArray);
		
		if(list!=null||list.size()!=0)
        {
        	int length=list.size();
        	for(int i=0;i<length;i++)
        	{
        		tb_adminModel adminModel=(tb_adminModel) list.get(i);
        		AreaAdminAddOrChangeOrDeleteSpotAdminModel addOrChangeOrDeleteSpotAdminModel1=new AreaAdminAddOrChangeOrDeleteSpotAdminModel();
        		addOrChangeOrDeleteSpotAdminModel1.setAccount(adminModel.getAccount());
        		addOrChangeOrDeleteSpotAdminModel1.setAid(adminModel.getAid());
        		addOrChangeOrDeleteSpotAdminModel1.setSid(adminModel.getSid());
        		String Sid=addOrChangeOrDeleteSpotAdminModel1.getSid();
        		//System.out.println("aidString:"+sidString);
        		String sql1="select Spot from tb_spot where Sid=\""+Sid+"\"";
        		try {
        			addOrChangeOrDeleteSpotAdminModel1.setSpot((String) spotDao.querySpot(sql1));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
        		list1.add(addOrChangeOrDeleteSpotAdminModel1);
        	}
        	
        	
        }
		// TODO Auto-generated method stub
		return list1;
	}

}

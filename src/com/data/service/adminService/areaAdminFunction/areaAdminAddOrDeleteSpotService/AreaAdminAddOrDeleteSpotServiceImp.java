package com.data.service.adminService.areaAdminFunction.areaAdminAddOrDeleteSpotService;

import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.tb_spotDao;
import com.data.model.AreaAdminAddOrDeleteSpotModel;

public class AreaAdminAddOrDeleteSpotServiceImp implements AreaAdminAddOrDeleteSpotService{
	private tb_spotDao spotDao;
	private List<Object> list;
	private List<Object> param;
	
	public tb_spotDao getSpotDao() {
		return spotDao;
	}

	public void setSpotDao(tb_spotDao spotDao) {
		this.spotDao = spotDao;
	}

	@Override
	public List<Object> getSpotInformations(
			AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel) {
		// TODO Auto-generated method stub
		String sql="select tb_spot.Sid,tb_spot.Spot,tb_admin.Account,tb_spot.CreateTime from tb_admin , tb_spot where tb_admin.sid in(select sid from tb_spot where Arid in(select Arid from tb_admin WHERE Account=?) and del=?) and tb_admin.sid=tb_spot.sid";
		String Account=areaAdminAddOrDeleteSpotModel.getAccount();
		param=new LinkedList<Object>();
		param.add(Account);
		param.add(0);
        try {
			list=spotDao.query1(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}

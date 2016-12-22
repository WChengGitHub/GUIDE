package com.data.service.adminService.spotAdminFunctions.spotAdminCompleteSpotInformation;

import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.tb_spotDao;
import com.data.model.SpotAdminCompleteSpotInformationModel;
import com.data.model.tb_spotModel;

public class SpotAdminCompleteSpotInformationServiceImp implements
		SpotAdminCompleteSpotInformationService {
	private List<Object> param;
	private List<Object> list;
	private tb_spotDao spotDao;

	public tb_spotDao getSpotDao() {
		return spotDao;
	}

	public void setSpotDao(tb_spotDao spotDao) {
		this.spotDao = spotDao;
	}

	@Override
	public SpotAdminCompleteSpotInformationModel getSpotInformation(
			SpotAdminCompleteSpotInformationModel spotAdminCompleteSpotInformationModel) {
		// TODO Auto-generated method stub
		String sql="select Longitude,Latitude,Radius,Description,Voice from tb_spot where Sid=(select Sid from tb_admin where Account=?)";
		String Account=spotAdminCompleteSpotInformationModel.getAccount();
		SpotAdminCompleteSpotInformationModel spotAdminCompleteSpotInformationModel1=new SpotAdminCompleteSpotInformationModel();
		tb_spotModel spotModel=new tb_spotModel();
		param=new LinkedList<Object>();
		param.add(Account);
		try {
			list=spotDao.querySpotInformation(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(list!=null&&list.size()!=0)
		{
			spotModel= (tb_spotModel) list.get(0);
			spotAdminCompleteSpotInformationModel1.setLatitude(spotModel.getLatitude());
			spotAdminCompleteSpotInformationModel1.setLongitude(spotModel.getLongitude());
			spotAdminCompleteSpotInformationModel1.setRadius(spotModel.getRadius());
			spotAdminCompleteSpotInformationModel1.setDescription(spotModel.getDescription());
			spotAdminCompleteSpotInformationModel1.setVoice(spotModel.getVoice());
		}
		return spotAdminCompleteSpotInformationModel1;
	}

	@Override
	public boolean changeSpotInformation(
			SpotAdminCompleteSpotInformationModel spotAdminCompleteSpotInformationModel) {
		// TODO Auto-generated method stub
		String sql="update tb_spot set(Radius,Description,Voice) values(?,?,?) where Sid=(select Sid from tb_admin where Account=?)";
		String Account=spotAdminCompleteSpotInformationModel.getAccount();
		String Radius=spotAdminCompleteSpotInformationModel.getRadius();
		String Description=spotAdminCompleteSpotInformationModel.getDescription();
		String Voice=spotAdminCompleteSpotInformationModel.getVoice();
		param=new LinkedList<Object>();
		param.add(Radius);
		param.add(Description);
		param.add(Voice);
		param.add(Account);
		try {
			spotDao.update(sql, param);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}

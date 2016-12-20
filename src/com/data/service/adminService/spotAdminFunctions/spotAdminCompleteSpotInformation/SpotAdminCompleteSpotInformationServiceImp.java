package com.data.service.adminService.spotAdminFunctions.spotAdminCompleteSpotInformation;

import com.data.dao.singleForm.tb_spotDao;
import com.data.model.SpotAdminCompleteSpotInformationModel;

public class SpotAdminCompleteSpotInformationServiceImp implements
		SpotAdminCompleteSpotInformationService {
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
		return null;
	}

}

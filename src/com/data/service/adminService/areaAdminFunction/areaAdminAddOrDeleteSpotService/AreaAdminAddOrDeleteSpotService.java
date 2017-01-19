package com.data.service.adminService.areaAdminFunction.areaAdminAddOrDeleteSpotService;

import java.util.List;

import com.data.model.AreaAdminAddOrDeleteSpotModel;

public interface AreaAdminAddOrDeleteSpotService {
	public List<Object> getSpotInformations(AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel);
	public boolean deleteSpot(AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel);
	public boolean addSpot(AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel);
}

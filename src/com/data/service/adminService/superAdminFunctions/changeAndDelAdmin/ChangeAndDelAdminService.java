package com.data.service.adminService.superAdminFunctions.changeAndDelAdmin;

import java.util.List;

import com.data.model.ChangeAndDelAdminModel;
import com.data.model.tb_provinceModel;

public interface ChangeAndDelAdminService {
	public List<Object> getAdminRecord();
	public boolean deleteAdmin(ChangeAndDelAdminModel changeAndDelAdminModel);
	//public ChangeAndDelAdminModel getArid(ChangeAndDelAdminModel changeAndDelAdminModel);
	public boolean changeAdmin(ChangeAndDelAdminModel changeAndDelAdminModel);
	public List<Object> getProvinces();
	public List<Object> getCities(ChangeAndDelAdminModel changeAndDelAdminModel);
	public List<Object> getAreas(ChangeAndDelAdminModel changeAndDelAdminModel);
	//public List<Object> getSpot(ChangeAndDelAdminModel changeAndDelAdminModel);
}

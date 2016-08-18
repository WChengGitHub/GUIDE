package com.data.service.visitorService.changeAndDelAdminService;

import java.util.List;

import com.data.model.ChangeAndDelAdminModel;
import com.data.model.tb_provinceModel;

public interface ChangeAndDelAdminService {
	public List<Object> getadminRecord();
	public boolean setAdminDelStatus(ChangeAndDelAdminModel changeAndDelAdminModel);
	public ChangeAndDelAdminModel getSid(ChangeAndDelAdminModel changeAndDelAdminModel);
	public boolean setChangeRecord(ChangeAndDelAdminModel changeAndDelAdminModel);
	public List<Object> getProvinceModel();
	public List<Object> getCity(ChangeAndDelAdminModel changeAndDelAdminModel);
	public List<Object> getArea(ChangeAndDelAdminModel changeAndDelAdminModel);
	public List<Object> getSpot(ChangeAndDelAdminModel changeAndDelAdminModel);
}

package com.data.service.visitorService.changeAndDelAdminService;

import java.util.List;

import com.data.model.ChangeAndDelAdminModel;

public interface ChangeAndDelAdminService {
	public List<Object> getadminRecord();
	public boolean setAdminDelStatus(ChangeAndDelAdminModel changeAndDelAdminModel);
	public ChangeAndDelAdminModel getSid(ChangeAndDelAdminModel changeAndDelAdminModel);
	public boolean setChangeRecord(ChangeAndDelAdminModel changeAndDelAdminModel);
}

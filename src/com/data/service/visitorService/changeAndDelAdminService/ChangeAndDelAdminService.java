package com.data.service.visitorService.changeAndDelAdminService;

import java.util.List;

public interface ChangeAndDelAdminService {
	public List<Object> getadminRecord();
	public boolean setAdminDelStatus(String Aid);
}

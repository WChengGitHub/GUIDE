package com.data.service.adminService.checkAdminFunctions.checkAdvice;

import java.util.List;

import com.data.model.CheckAdviceModel;

public interface CheckAdviceService {
	public int getAdviceNumber(CheckAdviceModel checkAdviceModel);
	public List<Object> getAdvices();
	public List<Object> getSuperAdminAids();
	public boolean setAdminAdvice(CheckAdviceModel checkAdviceModel);
}

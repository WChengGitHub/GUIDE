package com.data.service.adminService.checkAdminFunctions.checkAdvice;

import java.util.List;

import com.data.model.CheckAdviceModel;

public interface CheckAdviceService {
	public int getAdviceNumber(CheckAdviceModel checkAdviceModel);
	public List<Object> getAdvices();
	public List<Object> getSuperAdminAids();
	public List<Object> getSpotAdminAids(CheckAdviceModel checkAdviceModel);
	public boolean setAdminAdvice(CheckAdviceModel checkAdviceModel);
	public List<Object> getProvince();
	public List<Object> getCity(CheckAdviceModel checkAdviceModel);
	public List<Object> getArea(CheckAdviceModel checkAdviceModel);
	public boolean changeAdviceStatus(CheckAdviceModel checkAdviceModel);
	public String getVisitorEmail(CheckAdviceModel checkAdviceModel);
}

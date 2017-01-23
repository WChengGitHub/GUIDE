package com.data.controller.adminController.areaAdminController.areaAdminReplyAdviceController;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.model.AreaAdminAddOrChangeOrDeleteSpotAdminModel;
import com.data.service.adminService.areaAdminFunction.areaAdminAddOrChangeOrDeleteSpotAdmin.AreaAdminAddOrChangeOrDeleteSpotAdminService;

public class AreaAdminAddOrUpdateOrDeleteSpotAdminController {
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrChangeOrDeleteSpotAdminService areaAdminAddOrChangeOrDeleteSpotAdminService=(AreaAdminAddOrChangeOrDeleteSpotAdminService) factory.getBean("areaAdminAddOrUpdateOrDeleteSpotAdmin");
		AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel=new AreaAdminAddOrChangeOrDeleteSpotAdminModel();
		areaAdminAddOrChangeOrDeleteSpotAdminModel.setAccount("hh");
		List<AreaAdminAddOrChangeOrDeleteSpotAdminModel> list;
		list = areaAdminAddOrChangeOrDeleteSpotAdminService
				.getSpotAdminInformations(areaAdminAddOrChangeOrDeleteSpotAdminModel);
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray);
	}
}

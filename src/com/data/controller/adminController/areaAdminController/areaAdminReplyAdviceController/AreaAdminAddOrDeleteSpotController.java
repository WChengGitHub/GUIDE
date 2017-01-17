package com.data.controller.adminController.areaAdminController.areaAdminReplyAdviceController;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.model.AreaAdminAddOrDeleteSpotModel;
import com.data.service.adminService.areaAdminFunction.areaAdminAddOrDeleteSpotService.AreaAdminAddOrDeleteSpotService;

@RequestMapping("/AreaAdminAddOrDeleteSpotController")
@Controller
public class AreaAdminAddOrDeleteSpotController {
	@RequestMapping("/getSpotInformations")
	@ResponseBody
	public JSONArray getSpotInformations(
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		if (Account.isEmpty())
			return null;
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrDeleteSpotService areaAdminAddOrDeleteSpotService = (AreaAdminAddOrDeleteSpotService) factory
				.getBean("areaAdminAddOrDeleteSpotService");
		AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel = new AreaAdminAddOrDeleteSpotModel();
		List<Object> list;
		areaAdminAddOrDeleteSpotModel.setAccount(Account);
		list = areaAdminAddOrDeleteSpotService
				.getSpotInformations(areaAdminAddOrDeleteSpotModel);
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray);
		return jsonArray;
	}

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrDeleteSpotService areaAdminAddOrDeleteSpotService = (AreaAdminAddOrDeleteSpotService) factory
				.getBean("areaAdminAddOrDeleteSpotService");
		AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel = new AreaAdminAddOrDeleteSpotModel();
		List<Object> list;
		areaAdminAddOrDeleteSpotModel.setAccount("hh");
		list = areaAdminAddOrDeleteSpotService
				.getSpotInformations(areaAdminAddOrDeleteSpotModel);
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray);
	}
}

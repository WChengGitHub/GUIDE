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

	@RequestMapping("/deleteSpot")
	@ResponseBody
	public String deleteSpot(
			@RequestParam(value = "Account", required = false) String Account,@RequestParam(value = "Sid", required = false) String Sid)
			throws IOException {
		if (Sid.isEmpty())
			return null;
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrDeleteSpotService areaAdminAddOrDeleteSpotService = (AreaAdminAddOrDeleteSpotService) factory
				.getBean("areaAdminAddOrDeleteSpotService");
		AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel = new AreaAdminAddOrDeleteSpotModel();
		areaAdminAddOrDeleteSpotModel.setAccount(Account);
		areaAdminAddOrDeleteSpotModel.setSid(Sid);
		if(areaAdminAddOrDeleteSpotService.deleteSpot(areaAdminAddOrDeleteSpotModel))
		{
			String json = "{\"Status\":true}";
			return json;
		}
		return null;
	}
	@RequestMapping("/addSpot")
	@ResponseBody
	public String addSpot(
			@RequestParam(value = "Account", required = false) String Account,@RequestParam(value = "Spot", required = false) String Spot,@RequestParam(value = "Longitude", required = false) String Longitude,@RequestParam(value = "Latitude", required = false) String Latitude)
			throws IOException {
		if (Account.isEmpty()||Spot.isEmpty()||Longitude.isEmpty()||Latitude.isEmpty())
			return null;
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		Spot = java.net.URLDecoder.decode(Spot, "UTF-8");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrDeleteSpotService areaAdminAddOrDeleteSpotService = (AreaAdminAddOrDeleteSpotService) factory
				.getBean("areaAdminAddOrDeleteSpotService");
		AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel = new AreaAdminAddOrDeleteSpotModel();
		areaAdminAddOrDeleteSpotModel.setAccount(Account);
		areaAdminAddOrDeleteSpotModel.setSpot(Spot);
		areaAdminAddOrDeleteSpotModel.setLongitude(Longitude);
		areaAdminAddOrDeleteSpotModel.setLatitude(Latitude);
		if(areaAdminAddOrDeleteSpotService.addSpot(areaAdminAddOrDeleteSpotModel))
		{
			String json = "{\"Status\":true}";
			return json;
		}
		return null;
	}
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrDeleteSpotService areaAdminAddOrDeleteSpotService = (AreaAdminAddOrDeleteSpotService) factory
				.getBean("areaAdminAddOrDeleteSpotService");
		AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel = new AreaAdminAddOrDeleteSpotModel();
		areaAdminAddOrDeleteSpotModel.setAccount("hh");
		//areaAdminAddOrDeleteSpotModel.setSpot("五");
		//areaAdminAddOrDeleteSpotModel.setLongitude("1");
		//areaAdminAddOrDeleteSpotModel.setLatitude("2");
		//areaAdminAddOrDeleteSpotService.addSpot(areaAdminAddOrDeleteSpotModel);
		List<Object> list;
		//areaAdminAddOrDeleteSpotModel.setAccount("hh");
		//areaAdminAddOrDeleteSpotModel.setSid("4");
		//areaAdminAddOrDeleteSpotService.deleteSpot(areaAdminAddOrDeleteSpotModel);
		list = areaAdminAddOrDeleteSpotService
				.getSpotInformations(areaAdminAddOrDeleteSpotModel);
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray);
	}
}

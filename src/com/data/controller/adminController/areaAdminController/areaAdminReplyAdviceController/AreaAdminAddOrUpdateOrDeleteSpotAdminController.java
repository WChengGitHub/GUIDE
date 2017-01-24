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

import com.data.model.AreaAdminAddOrChangeOrDeleteSpotAdminModel;
import com.data.model.AreaAdminAddOrDeleteSpotModel;
import com.data.service.adminService.areaAdminFunction.areaAdminAddOrChangeOrDeleteSpotAdmin.AreaAdminAddOrChangeOrDeleteSpotAdminService;
import com.data.service.adminService.areaAdminFunction.areaAdminAddOrDeleteSpotService.AreaAdminAddOrDeleteSpotService;
@RequestMapping("/AreaAdminAddOrUpdateOrDeleteSpotAdminController")
@Controller
public class AreaAdminAddOrUpdateOrDeleteSpotAdminController {
	@RequestMapping("/getSpotAdmins")
	@ResponseBody
	public JSONArray getSpotAdmins(
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		if (Account.isEmpty())
			return null;
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrChangeOrDeleteSpotAdminService areaAdminAddOrChangeOrDeleteSpotAdminService=(AreaAdminAddOrChangeOrDeleteSpotAdminService) factory.getBean("areaAdminAddOrUpdateOrDeleteSpotAdmin");
		AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel=new AreaAdminAddOrChangeOrDeleteSpotAdminModel();
		areaAdminAddOrChangeOrDeleteSpotAdminModel.setAccount(Account);
		List<AreaAdminAddOrChangeOrDeleteSpotAdminModel> list;
		list = areaAdminAddOrChangeOrDeleteSpotAdminService
				.getSpotAdminInformations(areaAdminAddOrChangeOrDeleteSpotAdminModel);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}
	
	@RequestMapping("/getSpots")
	@ResponseBody
	public JSONArray getSpots(
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		if (Account.isEmpty())
			return null;
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrChangeOrDeleteSpotAdminService areaAdminAddOrChangeOrDeleteSpotAdminService=(AreaAdminAddOrChangeOrDeleteSpotAdminService) factory.getBean("areaAdminAddOrUpdateOrDeleteSpotAdmin");
		AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel=new AreaAdminAddOrChangeOrDeleteSpotAdminModel();
		areaAdminAddOrChangeOrDeleteSpotAdminModel.setAccount(Account);
		List<Object> list;
		list = areaAdminAddOrChangeOrDeleteSpotAdminService
				.getSpotInformations(areaAdminAddOrChangeOrDeleteSpotAdminModel);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}
	@RequestMapping("/getSpotAdmins1")
	@ResponseBody
	public JSONArray getSpotAdmins1(
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		if (Account.isEmpty())
			return null;
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrChangeOrDeleteSpotAdminService areaAdminAddOrChangeOrDeleteSpotAdminService=(AreaAdminAddOrChangeOrDeleteSpotAdminService) factory.getBean("areaAdminAddOrUpdateOrDeleteSpotAdmin");
		AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel=new AreaAdminAddOrChangeOrDeleteSpotAdminModel();
		areaAdminAddOrChangeOrDeleteSpotAdminModel.setAccount(Account);
		List<Object> list;
		//list = areaAdminAddOrChangeOrDeleteSpotAdminService
		//		.getSpotAdminInformations(areaAdminAddOrChangeOrDeleteSpotAdminModel);
		list=areaAdminAddOrChangeOrDeleteSpotAdminService.getSpotAdminInformations1(areaAdminAddOrChangeOrDeleteSpotAdminModel);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}
	
	@RequestMapping("/changeAdmin")
	@ResponseBody
	public String changeAdmin(
			@RequestParam(value = "Aid", required = false) String Aid,@RequestParam(value = "Sid", required = false) String Sid,@RequestParam(value = "newAid", required = false) String newAid)
			throws IOException {
		if (Aid.isEmpty()||Sid.isEmpty()||newAid.isEmpty())
			return null;
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrChangeOrDeleteSpotAdminService areaAdminAddOrChangeOrDeleteSpotAdminService=(AreaAdminAddOrChangeOrDeleteSpotAdminService) factory.getBean("areaAdminAddOrUpdateOrDeleteSpotAdmin");
		AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel=new AreaAdminAddOrChangeOrDeleteSpotAdminModel();
		areaAdminAddOrChangeOrDeleteSpotAdminModel.setAid(Aid);
		areaAdminAddOrChangeOrDeleteSpotAdminModel.setSid(Sid);
		areaAdminAddOrChangeOrDeleteSpotAdminModel.setNewAid(newAid);
		if(areaAdminAddOrChangeOrDeleteSpotAdminService.changeAdmin(areaAdminAddOrChangeOrDeleteSpotAdminModel))
		{
			String json = "{\"Status\":true}";
			return json;
		}
		return null;
	}
	
	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public String deleteAdmin(
			@RequestParam(value = "Aid", required = false) String Aid,@RequestParam(value = "Sid", required = false) String Sid)
			throws IOException {
		if (Aid.isEmpty()||Sid.isEmpty())
			return null;
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrChangeOrDeleteSpotAdminService areaAdminAddOrChangeOrDeleteSpotAdminService=(AreaAdminAddOrChangeOrDeleteSpotAdminService) factory.getBean("areaAdminAddOrUpdateOrDeleteSpotAdmin");
		AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel=new AreaAdminAddOrChangeOrDeleteSpotAdminModel();
		areaAdminAddOrChangeOrDeleteSpotAdminModel.setAid(Aid);
		areaAdminAddOrChangeOrDeleteSpotAdminModel.setSid(Sid);
		if(areaAdminAddOrChangeOrDeleteSpotAdminService.deleteAdmin(areaAdminAddOrChangeOrDeleteSpotAdminModel))
		{
			String json = "{\"Status\":true}";
			return json;
		}
		return null;
	}
	
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AreaAdminAddOrChangeOrDeleteSpotAdminService areaAdminAddOrChangeOrDeleteSpotAdminService=(AreaAdminAddOrChangeOrDeleteSpotAdminService) factory.getBean("areaAdminAddOrUpdateOrDeleteSpotAdmin");
		AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel=new AreaAdminAddOrChangeOrDeleteSpotAdminModel();
		areaAdminAddOrChangeOrDeleteSpotAdminModel.setAccount("hh");
		//List<AreaAdminAddOrChangeOrDeleteSpotAdminModel> list;
		List<Object> list;
		//list = areaAdminAddOrChangeOrDeleteSpotAdminService
		//		.getSpotAdminInformations(areaAdminAddOrChangeOrDeleteSpotAdminModel);
		list=areaAdminAddOrChangeOrDeleteSpotAdminService.getSpotAdminInformations1(areaAdminAddOrChangeOrDeleteSpotAdminModel);
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray);
	}
}

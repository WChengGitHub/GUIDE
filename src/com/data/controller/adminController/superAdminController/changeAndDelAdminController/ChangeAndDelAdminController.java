package com.data.controller.adminController.superAdminController.changeAndDelAdminController;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.model.ChangeAndDelAdminModel;
import com.data.service.adminService.superAdminFunctions.changeAndDelAdmin.ChangeAndDelAdminService;

@Controller
public class ChangeAndDelAdminController {
	private static List<Object> list;

	@RequestMapping("/getAdminRecordList")
	@ResponseBody
	public JSONArray getAdminRecords() {
		
		System.out.println("test                      test!!!");
		
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		list = changeAndDelAdminService.getAdminRecord();
		if (list.isEmpty())
			return null;
		// System.out.println("adminRecordList:" + adminRecordList.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		return jsonArray;
	}

	@RequestMapping("/setAdminDelStatus")
	@ResponseBody
	public String deleteAdmin(
			@RequestParam(value = "Email", required = false) String Email,
			@RequestParam(value = "Aid", required = false) String Aid,
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		
		if (Aid.isEmpty() || Email.isEmpty() || Account.isEmpty() ) {
			 System.out.println("Aid:"+Aid+" "+"Email:"+Email+"Account:"+Account);
			return null;
		}
		ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
		changeAndDelAdminModel.setAid(Aid);
		changeAndDelAdminModel.setEmail(Email);
		changeAndDelAdminModel.setAccount(Account);
		if (changeAndDelAdminService.deleteAdmin(changeAndDelAdminModel)) {
			String json = "{\"Status\":\"success\"}";

			return json;
		}

		return null;
	}

	@RequestMapping("/sendAdminChange")
	@ResponseBody
	public String changeAdmin(
			@RequestParam(value = "Email", required = false) String Email,
			@RequestParam(value = "Aid", required = false) String Aid,
			@RequestParam(value = "Privilege", required = false) String Privilege,
			@RequestParam(value = "Arid", required = false) String Arid,
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		// Spot = java.net.URLDecoder.decode(Spot, "UTF-8");
	    //System.out.println(Email+" "+Aid+" "+" "+Privilege+" "+Account);
		if (Aid.isEmpty() || Email.isEmpty() || Account.isEmpty()) {
			System.out.println("Aid:" + Aid + " " + "Email:" + Email
					+ "Account:" + Account);
			return null;
		}
		if (Arid.isEmpty() && Privilege.isEmpty()) {
			System.out.println("Arid:" + Arid + " " + "Privilege:" + Privilege);
			return null;
		}

		ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
		changeAndDelAdminModel.setArid(Arid);

		changeAndDelAdminModel.setPrivilege(Privilege);
		changeAndDelAdminModel.setAid(Aid);
		changeAndDelAdminModel.setEmail(Email);
		changeAndDelAdminModel.setAccount(Account);

		if (changeAndDelAdminService.changeAdmin(changeAndDelAdminModel)) {
			String json = "{\"Status\":\"success\"}";
			return json;
		}
		return null;
	}

	@RequestMapping("/getProvince")
	@ResponseBody
	public JSONArray getProvinces() {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		list = changeAndDelAdminService.getProvinces();
		if (list.isEmpty())
			return null;
		// System.out.println("adminRecordList:" + adminRecordList.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		return jsonArray;
	}

	@RequestMapping("/getCity")
	@ResponseBody
	public JSONArray getCities(
			@RequestParam(value = "Pid", required = false) String Pid) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		if (Pid.isEmpty())
		{
			System.out.println("Pid:"+Pid);
			return null;
		}
		ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
		changeAndDelAdminModel.setPid(Pid);
		list = changeAndDelAdminService.getCities(changeAndDelAdminModel);
		if (list.isEmpty())
		{
			System.out.println("list:"+list);
			return null;
		}
		// System.out.println("adminRecordList:" + list.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		return jsonArray;
	}

	@RequestMapping("/getAreas")
	@ResponseBody
	public JSONArray getArea(
			@RequestParam(value = "Cid", required = false) String Cid) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		
		if (Cid.isEmpty())
		{
			System.out.println("Cid:" + Cid);
			return null;
		}
			
			
		ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
		changeAndDelAdminModel.setCid(Cid);
		list = changeAndDelAdminService.getAreas(changeAndDelAdminModel);
		if (list.isEmpty())
			return null;
		// System.out.println("adminRecordList:" + adminRecordList.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println("jsonArray:"+jsonArray);
		return jsonArray;
	}
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");

		// adminRecordList = changeAndDelAdminService.getadminRecord();
		// System.out.println("adminRecordList:" + adminRecordList.size());
		// JSONArray jsonArray = JSONArray.fromObject(adminRecordList);
		// System.out.println(jsonArray);
		// list = changeAndDelAdminService.getProvinceModel();
		// System.out.println("adminRecordList:" + list.size());
		// JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
		// changeAndDelAdminModel.setPid("1");
		// changeAndDelAdminModel.setCid("2");
		// changeAndDelAdminModel.setArid("4");
		// list = changeAndDelAdminService.getCity(changeAndDelAdminModel);
		// System.out.println("adminRecordList:" + list.size());
		// JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		// list = changeAndDelAdminService.getArea(changeAndDelAdminModel);
		// System.out.println("adminRecordList:" + list.size());
		// JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		// list = changeAndDelAdminService.getSpot(changeAndDelAdminModel);
		// System.out.println("adminRecordList:" + list.size());
		// JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		// ChangeAndDelAdminModel changeAndDelAdminModel = new
		// ChangeAndDelAdminModel();
		// changeAndDelAdminModel.setAid("1");
		// System.out.println("changeAndDelAdminService.setAdminDelStatus:"
		// +
		// changeAndDelAdminService.setAdminDelStatus(changeAndDelAdminModel));
		changeAndDelAdminModel.setArid("3");
		// changeAndDelAdminModel.setArea("红花湖");
		changeAndDelAdminModel.setPrivilege("r");
		changeAndDelAdminModel.setAid("4");
		// System.out.println("Privilege:"+changeAndDelAdminModel.getPrivilege());
		// changeAndDelAdminModel=changeAndDelAdminService.getArid(changeAndDelAdminModel);
		System.out.println("Arid:" + changeAndDelAdminModel.getArid());
		System.out
				.println("Privilege:" + changeAndDelAdminModel.getPrivilege());
		System.out.println("changeAndDelAdminService.changeAndDelAdminModel:"

		+ changeAndDelAdminService.changeAdmin(changeAndDelAdminModel));
	}
}

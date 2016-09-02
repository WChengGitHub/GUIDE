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

import sun.org.mozilla.javascript.internal.Undefined;

import com.data.model.ChangeAndDelAdminModel;
import com.data.service.SendEmail;
import com.data.service.adminService.superAdminFunctions.changeAndDelAdmin.ChangeAndDelAdminService;


@Controller
public class ChangeAndDelAdminController {
	private static List<Object> list;

	@RequestMapping("/getAdminRecordList")
	@ResponseBody
	public JSONArray getAdminRecordList() {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		list = changeAndDelAdminService.getadminRecord();
		if (list == null)
			return null;
		// System.out.println("adminRecordList:" + adminRecordList.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		return jsonArray;
	}

	@RequestMapping("/setAdminDelStatus")
	@ResponseBody
	public String setAdminDelStatus(
			@RequestParam(value = "Email", required = false) String Email,
			@RequestParam(value = "Aid", required = false) String Aid,
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		/*
		 * System.out.println("Account:"+Account); System.out.println(Aid);
		 * System.out.println("Aid:"+Aid+" "+"Email:"+Email);
		 * System.out.println("length:"+Aid.length());
		 */
		if (Aid.length() == 0 || Email.length() == 0 || Account.length() == 0) {
			// System.out.println("Aid:"+Aid+" "+"Email:"+Email);
			return null;
		}
		ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
		changeAndDelAdminModel.setAid(Aid);
		changeAndDelAdminModel.setEmail(Email);
		changeAndDelAdminModel.setAccount(Account);
		if (changeAndDelAdminService.setAdminDelStatus(changeAndDelAdminModel)) {
//			SendEmail.sendEmail(Email, "超级管理员信件", "尊敬的" + Account
//					+ "，您的管理员权限已被取消");

			// long number = checkAdvice.getAdviceNumber();
			String json = "{\"Status\":\"success\"}";

			return json;
		}

		return null;
	}

	@RequestMapping("/sendAdminChange")
	@ResponseBody
	public String sendAdminChange(
			@RequestParam(value = "Email", required = false) String Email,
			@RequestParam(value = "Aid", required = false) String Aid,
			@RequestParam(value = "Privilege", required = false) String Privilege,
			@RequestParam(value = "Spot", required = false) String Spot,
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		Spot = java.net.URLDecoder.decode(Spot, "UTF-8");
		// System.out.println(Email+" "+Aid+" "+" "+Privilege+" "+Spot+" "+Account);
		if (Aid.length() == 0 || Email.length() == 0 || Account.length() == 0) {
			System.out.println("前台得到的数据有误");
			return null;
		}
		if (Spot.length() == 0 && Privilege.length() == 0)
			return null;
		// System.out.println("HelloWord!");
		ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
		changeAndDelAdminModel.setAid(Aid);
		changeAndDelAdminModel.setSpot(Spot);
		changeAndDelAdminModel.setPrivilege(Privilege);
		changeAndDelAdminModel.setEmail(Email);
		changeAndDelAdminModel.setAccount(Account);
		// System.out.println("111111111");
		if (changeAndDelAdminService.setChangeRecord(changeAndDelAdminModel)) {
			String json = "{\"Status\":\"success\"}";
			return json;
		}
		// System.out.println("2222222");
		System.out
				.println("changeAndDelAdminService.setChangeRecord(changeAndDelAdminModel):"
						+ changeAndDelAdminService
								.setChangeRecord(changeAndDelAdminModel));
		// System.out.println("3333333");
		return null;
	}

	@RequestMapping("/getProvince")
	@ResponseBody
	public JSONArray getProvince() {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		list = changeAndDelAdminService.getProvinceModel();
		if (list == null||list.size()==0)
			return null;
		// System.out.println("adminRecordList:" + adminRecordList.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		return jsonArray;
	}
	
	@RequestMapping("/getCity")
	@ResponseBody
	public JSONArray getCity(@RequestParam(value = "Pid", required = false) String Pid) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		if(Pid.length()==0)
			return null;
		ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
		changeAndDelAdminModel.setPid(Pid);
		list = changeAndDelAdminService.getCity(changeAndDelAdminModel);
		if (list == null||list.size()==0)
			return null;
		// System.out.println("adminRecordList:" + list.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		return jsonArray;
	}

	@RequestMapping("/getArea")
	@ResponseBody
	public JSONArray getArea(@RequestParam(value = "Cid", required = false) String Cid) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		System.out.println("Cid:"+Cid);
		if(Cid.length()==0)
			return null;
		ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
		changeAndDelAdminModel.setCid(Cid);
		list = changeAndDelAdminService.getArea(changeAndDelAdminModel);
		if (list == null||list.size()==0)
			return null;
		// System.out.println("adminRecordList:" + adminRecordList.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		//System.out.println("jsonArray:"+jsonArray);
		return jsonArray;
	}
	@RequestMapping("/getSpot")
	@ResponseBody
	public JSONArray getSpot(@RequestParam(value = "Arid", required = false) String Arid) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		System.out.println("Arid:"+Arid);
		if(Arid.length()==0)
			return null;
		ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
		changeAndDelAdminModel.setArid(Arid);
		list = changeAndDelAdminService.getSpot(changeAndDelAdminModel);
		if (list == null||list.size()==0)
			return null;
		System.out.println("adminRecordList:" + list.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		//System.out.println("jsonArray:"+jsonArray);
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
		changeAndDelAdminModel.setPid("1");
		changeAndDelAdminModel.setCid("2");
		changeAndDelAdminModel.setArid("4");
		// list = changeAndDelAdminService.getCity(changeAndDelAdminModel);
		// System.out.println("adminRecordList:" + list.size());
		// JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		// list = changeAndDelAdminService.getArea(changeAndDelAdminModel);
		// System.out.println("adminRecordList:" + list.size());
		// JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		list = changeAndDelAdminService.getSpot(changeAndDelAdminModel);
		System.out.println("adminRecordList:" + list.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray);
		// ChangeAndDelAdminModel changeAndDelAdminModel = new
		// ChangeAndDelAdminModel();
		// changeAndDelAdminModel.setAid("1");
		// System.out.println("changeAndDelAdminService.setAdminDelStatus:"
		// +
		// changeAndDelAdminService.setAdminDelStatus(changeAndDelAdminModel));
		// changeAndDelAdminModel.setSpot("惠州学院");
		// changeAndDelAdminModel.setPrivilege("c");
		// changeAndDelAdminModel.setAid("3");
		// changeAndDelAdminModel=changeAndDelAdminService.getSid(changeAndDelAdminModel);
		// System.out.println("Sid:"+changeAndDelAdminModel.getSid());
		// System.out.println("changeAndDelAdminService.changeAndDelAdminModel:"
		// + changeAndDelAdminService
		// .setChangeRecord(changeAndDelAdminModel));
	}
}

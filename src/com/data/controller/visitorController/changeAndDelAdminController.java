package com.data.controller.visitorController;

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

import com.data.service.SendEmail;
import com.data.service.visitorService.changeAndDelAdminService.ChangeAndDelAdminService;

@Controller
public class changeAndDelAdminController {
	private static List<Object> adminRecordList;

	@RequestMapping("/getAdminRecordList")
	@ResponseBody
	public JSONArray getAdminRecordList() {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		adminRecordList = changeAndDelAdminService.getadminRecord();
		if (adminRecordList == null)
			return null;
		// System.out.println("adminRecordList:" + adminRecordList.size());
		JSONArray jsonArray = JSONArray.fromObject(adminRecordList);
		System.out.println(jsonArray);
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
		if (Aid == null || Email == null)
			return null;
		if (changeAndDelAdminService.setAdminDelStatus(Aid)) {
			SendEmail.sendEmail(Email, "超级管理员信件", "尊敬的"+Account+"，您的管理员权限已被取消");

			// long number = checkAdvice.getAdviceNumber();
			String json = "{\"Status\":\"success\"}";

			return json;
		}
		

		return null;
	}

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ChangeAndDelAdminService changeAndDelAdminService = (ChangeAndDelAdminService) factory
				.getBean("ChangeAndDelAdminService");
		/*
		 * adminRecordList = changeAndDelAdminService.getadminRecord();
		 * System.out.println("adminRecordList:" + adminRecordList.size());
		 * JSONArray jsonArray = JSONArray.fromObject(adminRecordList);
		 * System.out.println(jsonArray);
		 */
		System.out.println("changeAndDelAdminService.setAdminDelStatus:"
				+ changeAndDelAdminService.setAdminDelStatus("1"));
	}
}

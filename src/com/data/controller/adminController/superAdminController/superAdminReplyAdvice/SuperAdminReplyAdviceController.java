package com.data.controller.adminController.superAdminController.superAdminReplyAdvice;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.model.SuperAdminReplyAdviceModel;
import com.data.service.adminService.superAdminFunctions.superAdminReplyAdvice.SuperAdminReplyAdviceService;

@RequestMapping("/SuperAdminReplyAdviceController")
@Controller
public class SuperAdminReplyAdviceController {
	@RequestMapping("/softwareAdviceNumber")
	@ResponseBody
	public String getAdviceNumber(
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		if (Account.length() == 0)
			return null;
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SuperAdminReplyAdviceService superAdminReplyAdviceService = (SuperAdminReplyAdviceService) factory
				.getBean("SuperAdminReplyAdviceService");
		SuperAdminReplyAdviceModel superAdminReplyAdviceModel = new SuperAdminReplyAdviceModel();
		superAdminReplyAdviceModel.setAccount(Account);
		int softwareAdviceNumber = superAdminReplyAdviceService
				.getSoftwareAdviceNumber(superAdminReplyAdviceModel);

		String json = "{\"softwareAdviceNumber\":" + softwareAdviceNumber + "}";
		return json;
	}

	@RequestMapping("/getSoftwareAdvices")
	@ResponseBody
	public JSONArray getAdvices(
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		if (Account == null || Account.length() == 0)
			return null;
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SuperAdminReplyAdviceService superAdminReplyAdviceService = (SuperAdminReplyAdviceService) factory
				.getBean("SuperAdminReplyAdviceService");
		SuperAdminReplyAdviceModel superAdminReplyAdviceModel = new SuperAdminReplyAdviceModel();
		superAdminReplyAdviceModel.setAccount(Account);
		JSONArray jsonArray = superAdminReplyAdviceService
				.getSoftwareAdvices(superAdminReplyAdviceModel);

		return jsonArray;
	}

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SuperAdminReplyAdviceService superAdminReplyAdviceService = (SuperAdminReplyAdviceService) factory
				.getBean("SuperAdminReplyAdviceService");
		SuperAdminReplyAdviceModel superAdminReplyAdviceModel = new SuperAdminReplyAdviceModel();
		superAdminReplyAdviceModel.setAccount("Hello");
		JSONArray jsonArray = superAdminReplyAdviceService
				.getSoftwareAdvices(superAdminReplyAdviceModel);
		System.out.println(jsonArray);
		// int softwareAdviceNumber = superAdminReplyAdviceService
		// .getSoftwareAdviceNumber(superAdminReplyAdviceModel);
		// System.out.println("softwareAdviceNumber:" + softwareAdviceNumber);

	}
}

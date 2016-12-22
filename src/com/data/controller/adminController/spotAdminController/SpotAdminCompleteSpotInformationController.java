package com.data.controller.adminController.spotAdminController;

import java.io.IOException;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.model.SpotAdminCompleteSpotInformationModel;
import com.data.model.SuperAdminReplyAdviceModel;
import com.data.service.adminService.spotAdminFunctions.spotAdminCompleteSpotInformation.SpotAdminCompleteSpotInformationService;
import com.data.service.adminService.superAdminFunctions.superAdminReplyAdvice.SuperAdminReplyAdviceService;

@RequestMapping("/SpotAdminCompleteSpotInformationController")
@Controller
public class SpotAdminCompleteSpotInformationController {
	@RequestMapping("/getSpotInformation")
	@ResponseBody
	public JSONArray getSpotInformation(
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		if (Account.isEmpty()) {
			System.out.println("Account:" + Account);
			return null;
		}
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		SpotAdminCompleteSpotInformationService spotAdminCompleteSpotInformationService = (SpotAdminCompleteSpotInformationService) factory
				.getBean("spotAdminCompleteSpotInformationService");
		SpotAdminCompleteSpotInformationModel spotAdminCompleteSpotInformationModel = new SpotAdminCompleteSpotInformationModel();
		spotAdminCompleteSpotInformationModel.setAccount(Account);
		spotAdminCompleteSpotInformationModel = spotAdminCompleteSpotInformationService
				.getSpotInformation(spotAdminCompleteSpotInformationModel);
		JSONArray jsonArray = JSONArray
				.fromObject(spotAdminCompleteSpotInformationModel);
		//System.out.println("jsonArray:" + jsonArray);
		return jsonArray;
	}

	@RequestMapping("/changeSpotInformation")
	@ResponseBody
	public String changeSpotInformation(
			@RequestParam(value = "Account", required = false) String Account,
			@RequestParam(value = "Radius", required = false) String Radius,
			@RequestParam(value = "Description", required = false) String Description,
			@RequestParam(value = "Voice", required = false) String Voice)
			throws IOException {
		if (Account.isEmpty()) {
			System.out.println("Account:" + Account);
			return null;
		}
		String json;
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		Description = java.net.URLDecoder.decode(Description, "UTF-8");
		Voice = java.net.URLDecoder.decode(Voice, "UTF-8");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		SpotAdminCompleteSpotInformationService spotAdminCompleteSpotInformationService = (SpotAdminCompleteSpotInformationService) factory
				.getBean("spotAdminCompleteSpotInformationService");
		SpotAdminCompleteSpotInformationModel spotAdminCompleteSpotInformationModel = new SpotAdminCompleteSpotInformationModel();
		spotAdminCompleteSpotInformationModel.setAccount(Account);
		spotAdminCompleteSpotInformationModel.setDescription(Description);
		spotAdminCompleteSpotInformationModel.setVoice(Voice);
		spotAdminCompleteSpotInformationModel.setRadius(Radius);
		boolean b = spotAdminCompleteSpotInformationService
				.changeSpotInformation(spotAdminCompleteSpotInformationModel);
		if (b) {
			json = "{\"Status\":\"success\"}";
			return json;
		}
		return null;
	}

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SpotAdminCompleteSpotInformationService spotAdminCompleteSpotInformationService = (SpotAdminCompleteSpotInformationService) factory
				.getBean("spotAdminCompleteSpotInformationService");
		SpotAdminCompleteSpotInformationModel spotAdminCompleteSpotInformationModel = new SpotAdminCompleteSpotInformationModel();
		spotAdminCompleteSpotInformationModel.setAccount("1");
		spotAdminCompleteSpotInformationModel.setDescription("hello");
		spotAdminCompleteSpotInformationModel.setVoice("World");
		spotAdminCompleteSpotInformationModel.setRadius("100");
		spotAdminCompleteSpotInformationService
				.changeSpotInformation(spotAdminCompleteSpotInformationModel);
		spotAdminCompleteSpotInformationModel = spotAdminCompleteSpotInformationService
				.getSpotInformation(spotAdminCompleteSpotInformationModel);
		JSONArray jsonArray = JSONArray
				.fromObject(spotAdminCompleteSpotInformationModel);
		System.out.println(jsonArray);
	}
}

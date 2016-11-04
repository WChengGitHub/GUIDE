package com.data.controller.visitorController;

import java.io.IOException;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.model.CompleteVisitorInformationModel;
import com.data.service.visitorService.completeVisitorInformationService.CompleteVisitorInformationService;

@RequestMapping("/CompleteVisitorInformationController")
@Controller
public class CompleteVisitorInformationController {

	/*
	 * @RequestMapping("/getGender")
	 * 
	 * @ResponseBody public String getGender(
	 * 
	 * @RequestParam(value = "Visitor", required = false) String Visitor) throws
	 * IOException { if (Visitor.isEmpty()) return null; Visitor =
	 * java.net.URLDecoder.decode(Visitor, "UTF-8");
	 * CompleteVisitorInformationModel completeVisitorInformationModel = new
	 * CompleteVisitorInformationModel(); ApplicationContext factory = new
	 * ClassPathXmlApplicationContext( "applicationContext.xml");
	 * completeVisitorInformationModel.setVisitor(Visitor);
	 * CompleteVisitorInformationService completeVisitorInformationService =
	 * (CompleteVisitorInformationService) factory
	 * .getBean("completeVisitorInformationService");
	 * completeVisitorInformationModel = completeVisitorInformationService
	 * .getGender(completeVisitorInformationModel); String json = "{\"Gender\":"
	 * + completeVisitorInformationModel.getGender() + "}"; return json; }
	 */

	@RequestMapping("/getVisitorInformation")
	@ResponseBody
	public JSONArray getVisitorInformation(
			@RequestParam(value = "Visitor", required = false) String Visitor)
			throws IOException {
		if (Visitor.isEmpty())
			return null;
		Visitor = java.net.URLDecoder.decode(Visitor, "UTF-8");
		CompleteVisitorInformationModel completeVisitorInformationModel = new CompleteVisitorInformationModel();
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		completeVisitorInformationModel.setVisitor(Visitor);
		CompleteVisitorInformationService completeVisitorInformationService = (CompleteVisitorInformationService) factory
				.getBean("completeVisitorInformationService");
		completeVisitorInformationModel = completeVisitorInformationService
				.getVisitorInformation(completeVisitorInformationModel);
		JSONArray jsonArray = JSONArray
				.fromObject(completeVisitorInformationModel);
		return jsonArray;
	}

	@RequestMapping("/judgeVisitor")
	@ResponseBody
	public String judgeVisitor(
			@RequestParam(value = "Visitor", required = false) String Visitor)
			throws IOException {
		if (Visitor.isEmpty())
			return null;
		int number = -1;
		Visitor = java.net.URLDecoder.decode(Visitor, "UTF-8");
		CompleteVisitorInformationModel completeVisitorInformationModel = new CompleteVisitorInformationModel();
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		completeVisitorInformationModel.setVisitor(Visitor);
		CompleteVisitorInformationService completeVisitorInformationService = (CompleteVisitorInformationService) factory
				.getBean("completeVisitorInformationService");
		number = completeVisitorInformationService
				.judgeVisitor(completeVisitorInformationModel);
		String json = "{\"number\":" + number + "}";
		return json;
	}

	@RequestMapping("/updateVisitorInformation")
	@ResponseBody
	public String updateVisitorInformation(
			@RequestParam(value = "Visitor", required = false) String Visitor,@RequestParam(value = "newVisitor", required = false) String newVisitor,@RequestParam(value = "Gender", required = false) String Gender)
			throws IOException {
		if (Visitor.isEmpty())
			return null;
		boolean sign =false;
		Visitor = java.net.URLDecoder.decode(Visitor, "UTF-8");
		newVisitor = java.net.URLDecoder.decode(newVisitor, "UTF-8");
		CompleteVisitorInformationModel completeVisitorInformationModel = new CompleteVisitorInformationModel();
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		completeVisitorInformationModel.setVisitor(Visitor);
		completeVisitorInformationModel.setNewVisitor(newVisitor);
		completeVisitorInformationModel.setGender(Gender);
		CompleteVisitorInformationService completeVisitorInformationService = (CompleteVisitorInformationService) factory
				.getBean("completeVisitorInformationService");
		sign = completeVisitorInformationService
				.updateVisitorInformation(completeVisitorInformationModel);
		String json = "{\"number\":" + sign + "}";
		return json;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CompleteVisitorInformationModel completeVisitorInformationModel = new CompleteVisitorInformationModel();
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		completeVisitorInformationModel.setVisitor("2");
		completeVisitorInformationModel.setNewVisitor("hello1");
		completeVisitorInformationModel.setGender("1");
		CompleteVisitorInformationService completeVisitorInformationService = (CompleteVisitorInformationService) factory
				.getBean("completeVisitorInformationService");
		// completeVisitorInformationModel = completeVisitorInformationService
		// .getGender(completeVisitorInformationModel);
		// System.out.println("Gender:"
		// + completeVisitorInformationModel.getGender());
		// completeVisitorInformationModel = completeVisitorInformationService
		// .getVisitorInformation(completeVisitorInformationModel);
		// JSONArray jsonArray = JSONArray
		// .fromObject(completeVisitorInformationModel);
		// System.out.println(jsonArray);
		System.out
				.println("Number:"
						+ completeVisitorInformationService
								.updateVisitorInformation(completeVisitorInformationModel));
	}

}

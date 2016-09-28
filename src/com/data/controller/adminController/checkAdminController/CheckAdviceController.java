package com.data.controller.adminController.checkAdminController;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.model.CheckAdviceModel;
import com.data.service.adminService.checkAdminFunctions.checkAdvice.CheckAdviceService;

@Controller
public class CheckAdviceController {
	
	@RequestMapping("/getCheckAdviceNumber")
	@ResponseBody
	public void getAdviceNumber(
			HttpServletResponse response,
			@RequestParam(value = "Privilege", required = false) String Privilege)
			throws IOException {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdviceService checkAdviceService = (CheckAdviceService) factory
				.getBean("CheckAdviceService");
		int adviceNumber = 0;
		if (Privilege.length() == 0) {
			System.out.println("管理员权限获取失败");
		} else {
			CheckAdviceModel checkAdviceModel = new CheckAdviceModel();
			checkAdviceModel.setPrivilege(Privilege);
			adviceNumber = checkAdviceService.getAdviceNumber(checkAdviceModel);
			System.out.println("AdviceNumber:" + adviceNumber);
		}

		Writer writer = response.getWriter();
		String json = "{\"adviceNumber\":" + adviceNumber + "}";
		writer.write(json);
	}

	@RequestMapping("/getCheckAdvices")
	@ResponseBody
	public JSONArray getCheckAdvices() throws IOException {
		// System.out.println("getAdviceRecordList");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdviceService checkAdviceService = (CheckAdviceService) factory
				.getBean("CheckAdviceService");
		List<Object> list;
		list = checkAdviceService.getAdvices();
		System.out.println(list);
		if (list != null) {
			// System.out.println(adviceRecordList.size());
			// System.out.println("数据提取成功");
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.out.println(jsonArray);
			// tb_adviceModel t=(tb_adviceModel) adviceRecordList.get(0);
			// System.out.println(t.getADid());
			return jsonArray;

		}

		return null;
	}

	@RequestMapping("/allocateAdvice")
	@ResponseBody
	public void allocateAdvice(HttpServletResponse response,
			@RequestParam(value = "Vid", required = false) String Vid,@RequestParam(value = "Status", required = false) String Status,@RequestParam(value = "type", required = false) String type,@RequestParam(value = "ADid", required = false) String ADid,@RequestParam(value = "Pid", required = false) String Pid,@RequestParam(value = "Cid", required = false) String Cid,@RequestParam(value = "Arid", required = false) String Arid)
			throws IOException {
		if(ADid.length()==0)
			return;
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdviceService checkAdviceService = (CheckAdviceService) factory
				.getBean("CheckAdviceService");
		System.out.println("Pid:"+Pid+" "+"Cid:"+Cid+"Arid:"+Arid);
		System.out.println("ADid:"+ADid+"type:"+type);
		System.out.println("Vid:"+Vid);
		CheckAdviceModel checkAdviceModel = new CheckAdviceModel();
		checkAdviceModel.setType(type);
		checkAdviceModel.setADid(ADid);
		checkAdviceModel.setPid(Pid);
		checkAdviceModel.setCid(Cid);
		checkAdviceModel.setArid(Arid);
		checkAdviceModel.setPrivilege(type);
		checkAdviceModel.setStatus(Status);
		checkAdviceModel.setVid(Vid);
		checkAdviceService.setAdminAdvice(checkAdviceModel);
		Writer writer = response.getWriter();
		String json = "{\"success\": \"yes\"}";
		writer.write(json);

	}
	@RequestMapping("/getSpotProvince")
	@ResponseBody
	public JSONArray getProvince() {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdviceService checkAdviceService = (CheckAdviceService) factory
				.getBean("CheckAdviceService");
		List<Object> list;
		list = checkAdviceService.getProvince();
		if (list == null||list.size()==0)
			return null;
		// System.out.println("adminRecordList:" + adminRecordList.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		return jsonArray;
	}
	
	@RequestMapping("/getSpotCity")
	@ResponseBody
	public JSONArray getCity(@RequestParam(value = "Pid", required = false) String Pid) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");


		CheckAdviceService checkAdviceService = (CheckAdviceService) factory
				.getBean("CheckAdviceService");
		CheckAdviceModel checkAdviceModel = new CheckAdviceModel();


		List<Object> list;
		
		if(Pid.length()==0)
			return null;
		
		checkAdviceModel.setPid(Pid);
		list = checkAdviceService.getCity(checkAdviceModel);
		if (list == null||list.size()==0)
			return null;
		// System.out.println("adminRecordList:" + list.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		return jsonArray;
	}

	@RequestMapping("/getSpotArea")
	@ResponseBody
	public JSONArray getArea(@RequestParam(value = "Cid", required = false) String Cid) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");


		CheckAdviceService checkAdviceService = (CheckAdviceService) factory
				.getBean("CheckAdviceService");
		CheckAdviceModel checkAdviceModel = new CheckAdviceModel();

		System.out.println("Cid:"+Cid);
		List<Object> list;
		if(Cid.length()==0)
			return null;
		
		checkAdviceModel.setCid(Cid);
		list = checkAdviceService.getArea(checkAdviceModel);
		if (list == null||list.size()==0)
			return null;
		// System.out.println("adminRecordList:" + adminRecordList.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		//System.out.println("jsonArray:"+jsonArray);
		return jsonArray;
	}
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdviceService checkAdviceService = (CheckAdviceService) factory
				.getBean("CheckAdviceService");
		CheckAdviceModel checkAdviceModel = new CheckAdviceModel();
		checkAdviceModel.setADid("13");
		checkAdviceModel.setStatus("f");
		checkAdviceModel.setVid("1");
		checkAdviceService.setAdminAdvice(checkAdviceModel);
//		checkAdviceModel.setType("a");
//		checkAdviceModel.setADid("2");
//		checkAdviceModel.setPrivilege("a");
//		checkAdviceModel.setStatus("p");
//		checkAdviceService.setAdminAdvice(checkAdviceModel);
		// List<Object> list;
		// list = checkAdviceService.getSuperAdminAids();
		// tb_adminModel adminModel=(tb_adminModel) list.get(0);
		// System.out.println(adminModel.getAid());
		// JSONArray jsonArray = JSONArray.fromObject(list);
		// System.out.println(jsonArray);
		/*
		 * checkAdviceModel.setPrivilege("c"); List<Object> list; list =
		 * checkAdviceService.getAdvices(); System.out.println(list); JSONArray
		 * jsonArray = JSONArray.fromObject(list);
		 * System.out.println(jsonArray);
		 */
		// System.out.println("AdviceNumber:"
		// + checkAdviceService.getAdviceNumber(checkAdviceModel));
//		checkAdviceModel.setPid("1");
//		checkAdviceModel.setCid("2");
//		checkAdviceModel.setArid("1");
//		       List<Object> list;
//				 list = checkAdviceService.getSpotAdminAids(checkAdviceModel);
//				 tb_adminModel adminModel=(tb_adminModel) list.get(0);
//				 System.out.println(adminModel.getAid());
//				 System.out.println(list.size());
//				 JSONArray jsonArray = JSONArray.fromObject(list);
//				 System.out.println(jsonArray);
	}
}
